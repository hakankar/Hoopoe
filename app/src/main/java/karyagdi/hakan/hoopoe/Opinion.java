package karyagdi.hakan.hoopoe;

import android.content.Context;
import android.graphics.Path;

import hakan.karyagdi.candyorm.library.CandyEntity;
import hakan.karyagdi.candyorm.library.annotations.DatabaseField;


/**
 * Created by hakan on 1/7/18.
 */

public class Opinion extends CandyEntity<Opinion> {


    @DatabaseField(FieldName = "ID",DataType = "INTEGER",PrimaryKey = true,AutoIncrement = true)
    private int opinionId;

    @DatabaseField(FieldName = "OPINOIN_DESC",DataType = "TEXT")
    private String opinionDesc;

    @DatabaseField(FieldName = "TYPE",DataType = "TEXT")
    private String opinionType;

    @DatabaseField(FieldName = "CREATE_DATE",DataType = "TEXT")
    private String createdDate;

    public Opinion()
    {

    }

    public Opinion (int opinionId, String opinionDesc, String opinionType, String createdDate)
    {
        this.opinionId=opinionId;
        this.opinionDesc = opinionDesc;
        this.opinionType=opinionType;
        this.createdDate=createdDate;
    }


    public int getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(int opinionId) {
        this.opinionId = opinionId;
    }

    public String getOpinionDesc() {
        return opinionDesc;
    }

    public void setOpinionDesc(String opinionDesc) {
        this.opinionDesc = opinionDesc;
    }

    public String getOpinionType() {
        return opinionType;
    }

    public void setOpinionType(String opinionType) {
        this.opinionType = opinionType;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
