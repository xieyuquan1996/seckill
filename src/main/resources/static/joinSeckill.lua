local userId = KEYS[1]
local goodsId = KEYS[2]
local pre = "seckill"
local val = redis.call("GET", pre .. ":" .. goodsId .. ":" .. userId)
--[[
     获取到秒杀商品的库存
--]]
local sum = tonumber(redis.call("HGET", "seckillGoods", goodsId))
local sumMessage = tonumber(redis.call("HGET", "seckillMessageLength", goodsId))
--[[
     seckillMessageLength用来存队列的长度，保证队列长度不能大于库存
--]]
if sumMessage == '' or (not sumMessage) then
    redis.call("HSET", "seckillMessageLength", goodsId, 0)
    sumMessage = 0
end
if val == '' or (not val) then
    if sum > 0  and sumMessage < sum then
        redis.call("SET", pre .. ":" .. goodsId .. ":" .. userId , 0)
        redis.call("HSET", "seckillMessageLength", goodsId, sumMessage + 1)
        return true
    end
    return false
end
return false