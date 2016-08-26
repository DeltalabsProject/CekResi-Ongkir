
package id.delta.cekresi.retrofit.models.resi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class Detail implements Serializable {

    @SerializedName("no_resi")
    @Expose
    private String noResi;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("asal")
    @Expose
    private Asal asal;
    @SerializedName("tujuan")
    @Expose
    private Tujuan tujuan;

    /**
     * 
     * @return
     *     The noResi
     */
    public String getNoResi() {
        return noResi;
    }

    /**
     * 
     * @param noResi
     *     The no_resi
     */
    public void setNoResi(String noResi) {
        this.noResi = noResi;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The service
     */
    public String getService() {
        return service;
    }

    /**
     * 
     * @param service
     *     The service
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 
     * @return
     *     The tanggal
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * 
     * @param tanggal
     *     The tanggal
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    /**
     * 
     * @return
     *     The asal
     */
    public Asal getAsal() {
        return asal;
    }

    /**
     * 
     * @param asal
     *     The asal
     */
    public void setAsal(Asal asal) {
        this.asal = asal;
    }

    /**
     * 
     * @return
     *     The tujuan
     */
    public Tujuan getTujuan() {
        return tujuan;
    }

    /**
     * 
     * @param tujuan
     *     The tujuan
     */
    public void setTujuan(Tujuan tujuan) {
        this.tujuan = tujuan;
    }

}
