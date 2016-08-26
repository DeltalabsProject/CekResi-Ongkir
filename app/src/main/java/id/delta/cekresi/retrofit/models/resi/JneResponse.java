
package id.delta.cekresi.retrofit.models.resi;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Generated("org.jsonschema2pojo")
public class JneResponse implements Serializable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("pesan")
    @Expose
    private String pesan;
    @SerializedName("query")
    @Expose
    private Query query;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("website")
    @Expose
    private String website;

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
     *     The pesan
     */
    public String getPesan() {
        return pesan;
    }

    /**
     * 
     * @param pesan
     *     The pesan
     */
    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    /**
     * 
     * @return
     *     The query
     */
    public Query getQuery() {
        return query;
    }

    /**
     * 
     * @param query
     *     The query
     */
    public void setQuery(Query query) {
        this.query = query;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 
     * @param website
     *     The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

}
