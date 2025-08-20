package org.example.game.game.constants;


public class ConstType {
    private Integer type;
    private Integer typeDetail;
    private String remark;

    public ConstType() {
    }

    ;

    public ConstType(Integer type, Integer typeDetail, String remark) {
        super();
        this.type = type;
        this.typeDetail = typeDetail;
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(Integer typeDetail) {
        this.typeDetail = typeDetail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
