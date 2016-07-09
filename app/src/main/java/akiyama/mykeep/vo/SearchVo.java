package akiyama.mykeep.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * FIXME
 *
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-07-29  16:24
 */
public class SearchVo implements Parcelable{
    private String id;
    private String name;
    private boolean isCheck;



    public SearchVo(String name,boolean isCheck){
        this.name=name;
        this.isCheck=isCheck;
    }
    public SearchVo(String id,String name,boolean isCheck){
        this.id = id;
        this.name=name;
        this.isCheck=isCheck;
    }

    public SearchVo(Parcel in){
        this.id = in.readString();
        this.name = in.readString();
        this.isCheck = (in.readByte()==1) ? true:false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeByte((byte) (isCheck ? 1 : 0));
    }

}
