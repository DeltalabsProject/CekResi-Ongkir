
package id.delta.cekresi.retrofit.models.resi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Riwayat implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("lokasi")
    @Expose
    private String lokasi;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    /**
     * 
     * @return
     *     The taggal
     */
    public String getTaggal() {
        return tanggal;
    }

    /**
     * 
     * @param taggal
     *     The taggal
     */
    public void setTaggal(String taggal) {
        this.tanggal = taggal;
    }

    /**
     * 
     * @return
     *     The lokasi
     */
    public String getLokasi() {
        return lokasi;
    }

    /**
     * 
     * @param lokasi
     *     The lokasi
     */
    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    /**
     * 
     * @return
     *     The keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * 
     * @param keterangan
     *     The keterangan
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

}
