package com.zh.framework.entity.data;

import com.zh.framework.annotation.Table;
import com.zh.framework.entity.DataEntity;

/**
 * Created by Mrkin on 2017/2/17.
 */
@Table(value = "tb_level")
public class TbLevel extends DataEntity {
    private int level;//等级
    private int isPush;//是否推送  1:是 0:否

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIsPush() {
        return isPush;
    }

    public void setIsPush(int isPush) {
        this.isPush = isPush;
    }
}
