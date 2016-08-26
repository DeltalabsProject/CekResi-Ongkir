
package id.delta.cekresi.retrofit.models.resi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Data implements Serializable {

    @SerializedName("detail")
    @Expose
    private Detail detail;
    @SerializedName("riwayat")
    @Expose
    private List<Riwayat> riwayat = new ArrayList<Riwayat>();

    /**
     * 
     * @return
     *     The detail
     */
    public Detail getDetail() {
        return detail;
    }

    /**
     * 
     * @param detail
     *     The detail
     */
    public void setDetail(Detail detail) {
        this.detail = detail;
    }

    /**
     * 
     * @return
     *     The riwayat
     */
    public List<Riwayat> getRiwayat() {
        return riwayat;
    }

    /**
     * 
     * @param riwayat
     *     The riwayat
     */
    public void setRiwayat(List<Riwayat> riwayat) {
        this.riwayat = riwayat;
    }

}
