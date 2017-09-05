/**
 * Created by ICHIRO on 04/09/2017.
 */
package tecsup.c15.c4.ichiro.honda.com.myapplicationpizza;

public class data {

    private String pizza,maza;
    private int precio, precio_extra;

    public data(String pizza,String maza, int precio, int precio_extra) {
        this.pizza = pizza;
        this.maza = maza;
        this.precio = precio;
        this.precio_extra = precio_extra;
    }

    public String getPizza() {
        return pizza;
    }

    public void setPizza(String pizza) {
        this.pizza = pizza;
    }

    public String getMaza() {
        return maza;
    }

    public void setMaza(String maza) {
        this.maza = maza;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getPrecio_extra() {
        return precio_extra;
    }

    public void setPrecio_extra(int precio_extra) {
        this.precio_extra = precio_extra;
    }


}
