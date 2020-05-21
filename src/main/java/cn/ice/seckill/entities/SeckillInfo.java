package cn.ice.seckill.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String goodsId;
    private String userId;
}
