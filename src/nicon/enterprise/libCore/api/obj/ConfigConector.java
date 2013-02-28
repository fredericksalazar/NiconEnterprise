/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.obj;

import java.io.Serializable;

public class ConfigConector
  implements Serializable
{
  private String URL;
  private String NAMECONECTION;
  private String IPSERVER = "127.0.0.1";
  private String PORT = "3306";
  private String DATABASE = "NiconEnterprise";
  private String USUARIO;
  private String CONTRASEÑA;
  private static final long serialVersionUID = 1L;

  public ConfigConector(String NAMECONECTION, String IPSERVER, String PORT, String DATABASE, String USUARIO, String CONTRASEÑA)
  {
    this.NAMECONECTION = NAMECONECTION;
    this.IPSERVER = IPSERVER;
    this.PORT = PORT;
    this.DATABASE = DATABASE;
    this.USUARIO = USUARIO;
    this.CONTRASEÑA = CONTRASEÑA;
    this.URL = ("jdbc:mysql://" + IPSERVER + ":" + PORT + "/" + DATABASE);
  }

  public String getNAMECONECTION()
  {
    return this.NAMECONECTION;
  }

  public void setNAMECONECTION(String NAMECONECTION)
  {
    this.NAMECONECTION = NAMECONECTION;
  }

  public String getCONTRASEÑA()
  {
    return this.CONTRASEÑA;
  }

  public void setCONTRASEÑA(String CONTRASEÑA)
  {
    this.CONTRASEÑA = CONTRASEÑA;
  }

  public String getDATABASE()
  {
    return this.DATABASE;
  }

  public void setDATABASE(String DATABASE)
  {
    this.DATABASE = DATABASE;
  }

  public String getURL()
  {
    return this.URL;
  }

  public void setURL(String SERVIDOR)
  {
    this.URL = SERVIDOR;
  }

  public String getUSUARIO()
  {
    return this.USUARIO;
  }

  public void setUSUARIO(String USUARIO)
  {
    this.USUARIO = USUARIO;
  }

  public String getIPSERVER()
  {
    return this.IPSERVER;
  }

  public void setIPSERVER(String IPSERVER)
  {
    this.IPSERVER = IPSERVER;
  }

  public String getPORT()
  {
    return this.PORT;
  }

  public void setPORT(String PORT)
  {
    this.PORT = PORT;
  }
}