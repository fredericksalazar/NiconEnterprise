/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;

import java.util.Date;

public class Producto
{
  private String Referencia;
  private String Nombre;
  private String Descripcion;
  private String Dimensiones;
  private double ValorCompra;
  private double ValorVentaCerrajero;
  private double ValorVentaMinimo;
  private double ValorVentaMaximo;
  private String Garantia;
  private String DocumentoRegistro;
  private Date FechaRegistro;
  private String CodigoLinea;
  private LineaProducto LineaProducto;

  public Producto(String Referencia, String Nombre, String Descripcion, String Dimensiones, double ValorCompra, double ValorVentaCerrajero, double ValorVentaMinimo, double ValorVentaMaximo, String Garantia, String DocumentoRegistro, String CodigoLinea)
  {
    this.Referencia = Referencia;
    this.Nombre = Nombre;
    this.Descripcion = Descripcion;
    this.Dimensiones = Dimensiones;
    this.ValorCompra = ValorCompra;
    this.ValorVentaCerrajero = ValorVentaCerrajero;
    this.ValorVentaMinimo = ValorVentaMinimo;
    this.ValorVentaMaximo = ValorVentaMaximo;
    this.Garantia = Garantia;
    this.DocumentoRegistro = DocumentoRegistro;
    this.CodigoLinea = CodigoLinea;
  }

  public Producto(String Referencia, String Nombre, String Descripcion, String Dimensiones, double ValorCompra, double ValorVentaCerrajero, double ValorVentaMinimo, double ValorVentaMaximo, String Garantia, String DocumentoRegistro, Date FechaRegistro, String CodigoLinea) {
    this.Referencia = Referencia;
    this.Nombre = Nombre;
    this.Descripcion = Descripcion;
    this.Dimensiones = Dimensiones;
    this.ValorCompra = ValorCompra;
    this.ValorVentaCerrajero = ValorVentaCerrajero;
    this.ValorVentaMinimo = ValorVentaMinimo;
    this.ValorVentaMaximo = ValorVentaMaximo;
    this.Garantia = Garantia;
    this.DocumentoRegistro = DocumentoRegistro;
    this.FechaRegistro = FechaRegistro;
    this.CodigoLinea = CodigoLinea;
  }

  public String getCodigoLinea() {
    return this.CodigoLinea;
  }

  public void setCodigoLinea(String CodigoLinea) {
    this.CodigoLinea = CodigoLinea;
  }

  public String getDescripcion() {
    return this.Descripcion;
  }

  public void setDescripcion(String Descripcion) {
    this.Descripcion = Descripcion;
  }

  public String getDimensiones() {
    return this.Dimensiones;
  }

  public void setDimensiones(String Dimensiones) {
    this.Dimensiones = Dimensiones;
  }

  public String getDocumentoRegistro() {
    return this.DocumentoRegistro;
  }

  public void setDocumentoRegistro(String DocumentoRegistro) {
    this.DocumentoRegistro = DocumentoRegistro;
  }

  public Date getFechaRegistro() {
    return this.FechaRegistro;
  }

  public void setFechaRegistro(Date FechaRegistro) {
    this.FechaRegistro = FechaRegistro;
  }

  public String getGarantia() {
    return this.Garantia;
  }

  public void setGarantia(String Garantia) {
    this.Garantia = Garantia;
  }

  public LineaProducto getLineaProducto() {
    return this.LineaProducto;
  }

  public void setLineaProducto(LineaProducto LineaProducto) {
    this.LineaProducto = LineaProducto;
  }

  public String getNombre() {
    return this.Nombre;
  }

  public void setNombre(String Nombre) {
    this.Nombre = Nombre;
  }

  public String getReferencia() {
    return this.Referencia;
  }

  public void setReferencia(String Referencia) {
    this.Referencia = Referencia;
  }

  public double getValorCompra() {
    return this.ValorCompra;
  }

  public void setValorCompra(double ValorCompra) {
    this.ValorCompra = ValorCompra;
  }

  public double getValorVentaCerrajero() {
    return this.ValorVentaCerrajero;
  }

  public void setValorVentaCerrajero(double ValorVentaCerrajero) {
    this.ValorVentaCerrajero = ValorVentaCerrajero;
  }

  public double getValorVentaMaximo() {
    return this.ValorVentaMaximo;
  }

  public void setValorVentaMaximo(double ValorVentaMaximo) {
    this.ValorVentaMaximo = ValorVentaMaximo;
  }

  public double getValorVentaMinimo() {
    return this.ValorVentaMinimo;
  }

  public void setValorVentaMinimo(double ValorVentaMinimo) {
    this.ValorVentaMinimo = ValorVentaMinimo;
  }

  public String toString()
  {
    return "Producto{Referencia=" + this.Referencia + ", Nombre=" + this.Nombre + ", Descripcion=" + this.Descripcion + ", Dimensiones=" + this.Dimensiones + ", ValorCompra=" + this.ValorCompra + ", ValorVentaCerrajero=" + this.ValorVentaCerrajero + ", ValorVentaMinimo=" + this.ValorVentaMinimo + ", ValorVentaMaximo=" + this.ValorVentaMaximo + ", Garantia=" + this.Garantia + ", DocumentoRegistro=" + this.DocumentoRegistro + ", FechaRegistro=" + this.FechaRegistro + ", CodigoLinea=" + this.CodigoLinea + ", LineaProducto=" + this.LineaProducto + '}';
  }
}
