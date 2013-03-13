/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nicon.enterprise.libCore.api.dao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import nicon.enterprise.libCore.api.util.NiconLibTools;
import nicon.enterprise.libCore.obj.ConfigConector;

public class ConfigConectorDAO {

    private ConfigConector config;
    private ConfigConectorDAO API;
    private boolean state;
    private String path = "./Config";
    private String nameFile = "Conector.conf";
    private ObjectInputStream inputObject;
    private ObjectOutputStream outputObject;

    public ConfigConectorDAO(ConfigConector config) {
        this.config = config;
    }

    public ConfigConectorDAO() {
    }

    public boolean createConfigFile() throws IOException {
        if (this.config != null) {
            if (NiconLibTools.verifyExistDir(this.path)) {
                NiconLibTools.writeObjectToFile(this.config, this.path, this.nameFile);
                state = true;
           } else {
                NiconLibTools.createDirectory(this.path);
                createConfigFile();
            }
        }
        return this.state;
    }

    public boolean addConfigConection() {
        try {
            if (this.config != null) {
                System.out.println("Iniciando adicion de configuracion de coneccion a Conector.conf");
                this.outputObject = NiconLibTools.getObjectFileToWrite(this.path + "/" + this.nameFile);
                this.outputObject.writeObject(this.config);
                this.outputObject.close();
                System.out.println("Adicion terminada ...");
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un ERROR en ConfigConectorDAO.addConfigConection():\n" + e.getMessage());
        }
        return this.state;
    }

    public boolean deleteConfigFile() {
        try {
            if (NiconLibTools.verifiExistFile(this.path + "/" + this.nameFile)) {
                if (NiconLibTools.deleteFile(this.path + "/" + this.nameFile)) {
                    this.state = true;
                } else {
                    this.state = false;
                }
            }
        } catch (Exception e) {
            System.err.println("Ocurrio un error en ConfigConectorDAO.deleteConfigFile():\n" + e);
        }
        return this.state;
    }

    public ConfigConector loadConfig() throws IOException, ClassNotFoundException {
        System.out.println("Cargando conector.conf desde: " + this.path + "/" + this.nameFile + " ...");
        this.inputObject = NiconLibTools.readFileObject(this.path + "/" + this.nameFile);
        this.config = ((ConfigConector) this.inputObject.readObject());
        this.inputObject.close();

        return this.config;
    }

    public void createConfigDefault() throws IOException {
        System.out.println("Creando configuracion por defecto para: Conector.conf ...");
        this.config = new ConfigConector("Conección Local", "127.0.0.1", "3306", "NiconEnterprise", "root", "1143825620");
        this.API = new ConfigConectorDAO(this.config);
        this.state = this.API.createConfigFile();        
    }

    public boolean verifyConfigFile() {
        System.out.println("Verificando existencia de Conector.conf en: " + this.path + "/" + this.nameFile + "...");
        return NiconLibTools.verifiExistFile(this.path + "/" + this.nameFile);
    }
}