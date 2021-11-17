package controller;

import java.io.IOException;

public interface IArquivosController {
	
	public void leDir(String caminho) throws IOException;
	public void leArquivo(String caminho, String arquivo) throws IOException;
	public void geraArquivo(String caminho, String arquivo) throws IOException;
	public void abreArquivo(String caminho, String arquivo) throws IOException;

}
