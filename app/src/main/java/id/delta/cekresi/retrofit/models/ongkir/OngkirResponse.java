
package id.delta.cekresi.retrofit.models.ongkir;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class OngkirResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    protected String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("service")
    @Expose
    private String service;
    @SerializedName("dari")
    @Expose
    private String dari;
    @SerializedName("ke")
    @Expose
    private String ke;
    @SerializedName("berat")
    @Expose
    private String berat;
    @SerializedName("ongkos")
    @Expose
    private List<Ongko> ongkos = new ArrayList<Ongko>();

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
     *     The dari
     */
    public String getDari() {
        return dari;
    }

    /**
     * 
     * @param dari
     *     The dari
     */
    public void setDari(String dari) {
        this.dari = dari;
    }

    /**
     * 
     * @return
     *     The ke
     */
    public String getKe() {
        return ke;
    }

    /**
     * 
     * @param ke
     *     The ke
     */
    public void setKe(String ke) {
        this.ke = ke;
    }

    /**
     * 
     * @return
     *     The berat
     */
    public String getBerat() {
        return berat;
    }

    /**
     * 
     * @param berat
     *     The berat
     */
    public void setBerat(String berat) {
        this.berat = berat;
    }

    /**
     * 
     * @return
     *     The ongkos
     */
    public List<Ongko> getOngkos() {
        return ongkos;
    }

    /**
     * 
     * @param ongkos
     *     The ongkos
     */
    public void setOngkos(List<Ongko> ongkos) {
        this.ongkos = ongkos;
    }

}
