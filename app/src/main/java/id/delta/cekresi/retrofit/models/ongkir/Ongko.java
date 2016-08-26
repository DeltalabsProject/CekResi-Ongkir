
package id.delta.cekresi.retrofit.models.ongkir;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Ongko {

    @SerializedName("layanan")
    @Expose
    private String layanan;
    @SerializedName("kiriman")
    @Expose
    private String kiriman;
    @SerializedName("tarif")
    @Expose
    private String tarif;
    @SerializedName("etd")
    @Expose
    private String etd;

    /**
     * 
     * @return
     *     The layana
     */
    public String getLayanan() {
        return layanan;
    }

    /**
     * 
     * @param layana
     *     The layana
     */
    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    /**
     * 
     * @return
     *     The kiriman
     */
    public String getKiriman() {
        return kiriman;
    }

    /**
     * 
     * @param kiriman
     *     The kiriman
     */
    public void setKiriman(String kiriman) {
        this.kiriman = kiriman;
    }

    /**
     * 
     * @return
     *     The tarif
     */
    public String getTarif() {
        return tarif;
    }

    /**
     * 
     * @param tarif
     *     The tarif
     */
    public void setTarif(String tarif) {
        this.tarif = tarif;
    }

    /**
     * 
     * @return
     *     The etd
     */
    public String getEtd() {
        return etd;
    }

    /**
     * 
     * @param etd
     *     The etd
     */
    public void setEtd(String etd) {
        this.etd = etd;
    }

}
