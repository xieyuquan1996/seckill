local userId = KEYS[1]
local goodsId = KEYS[2]
local pre = "seckill"
local val = tonumber(redis.call("get", pre .. ":" .. goodsId .. ":" .. userId))
local sum = tonumber(redis.call("HGET", "seckillGoods", goodsId))
local sumMessage = tonumber(redis.call("HGET", "seckillMessageLength", goodsId))
local result = false
if sum == '' or (not sum) then
    return false
end
if val < 1 then
    if sum > 0  then
        redis.call("set", pre .. ":" .. goodsId .. ":" .. userId, 1)
        redis.call("HSET", "seckillGoods", goodsId, sum - 1)
        redis.call("HSET", "seckillMessageLength", goodsId, sumMessage - 1)
        result =  true
    else
        redis.call("set",  pre .. ":" .. goodsId .. ":" .. userId, 2)
    end
end
return result