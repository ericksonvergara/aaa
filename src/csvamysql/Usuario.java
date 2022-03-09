/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csvamysql;

/**
 *
 * @author xcheko51x
 */
public class Usuario {
    
    String factura;
    String valor;
    String valor_adicional;
    String transaccion;
    String fecha_pago;

    public Usuario(String factura, String valor, String valor_adicional, String transaccion, String fecha_pago) {
        this.factura = factura;
        this.valor = valor;
        this.valor_adicional = valor_adicional;
        this.transaccion = transaccion;
        this.fecha_pago = fecha_pago;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setValor_adicional(String valor_adicional) {
        this.valor_adicional = valor_adicional;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getFactura() {
        return factura;
    }

    public String getValor() {
        return valor;
    }

    public String getValor_adicional() {
        return valor_adicional;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }
    
}
