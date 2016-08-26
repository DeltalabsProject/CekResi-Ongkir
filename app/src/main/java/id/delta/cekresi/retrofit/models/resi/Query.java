
package id.delta.cekresi.retrofit.models.resi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Query {

    @SerializedName("pengirim")
    @Expose
    private String pengirim;
    @SerializedName("resi")
    @Expose
    private String resi;

    /**
     * 
     * @return
     *     The pengirim
     */
    public String getPengirim() {
        return pengirim;
    }

    /**
     * 
     * @param pengirim
     *     The pengirim
     */
    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    /**
     * 
     * @return
     *     The resi
     */
    public String getResi() {
        return resi;
    }

    /**
     * 
     * @param resi
     *     The resi
     */
    public void setResi(String resi) {
        this.resi = resi;
    }

}
