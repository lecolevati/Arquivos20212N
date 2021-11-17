package view;

import java.io.IOException;

import controller.ArquivosController;
import controller.IArquivosController;

public class Principal {

	public static void main(String[] args) {
		IArquivosController arqCont = new ArquivosController();
//		String caminho = "C:\\Windows";
		String caminho = "C:\\TEMP";
		String arquivo = "cadastro.csv";
		try {
//			arqCont.leDir(caminho);
//			arqCont.geraArquivo(caminho, arquivo);
			arqCont.leArquivo(caminho, arquivo);
//			arqCont.abreArquivo(caminho, arquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
