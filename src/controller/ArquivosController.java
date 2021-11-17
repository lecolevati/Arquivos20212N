package controller;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ArquivosController implements IArquivosController {

	public ArquivosController() {
		super();
	}

	@Override
	public void leDir(String caminho) throws IOException {
		File dir = new File(caminho);
		if (dir.exists() && dir.isDirectory()) {
			File[] vetFile = dir.listFiles();
			for (File f : vetFile) {
				String nome = "";
				if (f.isDirectory()) {
					nome = "<DIR> ";
				} else {
					nome = "      ";
				}
				nome = nome + f.getName();
				System.out.println(nome);
			}
		} else {
			throw new IOException("Diretório inválido");
		}
	}

	@Override
	public void leArquivo(String caminho, String arquivo) throws IOException {
		File arq = new File(caminho, arquivo);
		if (arq.exists() && arq.isFile()) {
			FileInputStream fluxo = new FileInputStream(arq);
			InputStreamReader leFluxo = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leFluxo);
			String linha = buffer.readLine();
			while (linha != null) {
				if (linha.contains(";")) {
					String[] vetLinha = linha.split(";");
					System.out.print(vetLinha[0]+"\t");
					if (vetLinha[1].length() > 7) {
						System.out.println(vetLinha[1]+"\t");
					} else {
						System.out.println(vetLinha[1]+"\t\t");
					}
					System.out.println(vetLinha[2]);
				}
				linha = buffer.readLine();
			}
			buffer.close();
			leFluxo.close();
			fluxo.close();
		} else {
			throw new IOException("Arquivo inválido");
		}
	}

	@Override
	public void geraArquivo(String caminho, String arquivo) throws IOException {
		File dir = new File(caminho);
		if (dir.exists() && dir.isDirectory()) {
			File arq = new File(caminho, arquivo);
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			String conteudo = geraConteudo();
			
			escrita(arq, existe, conteudo);
		} else {
			throw new IOException("Diretório inválido");
		}
			
	}

	private void escrita(File arq, boolean existe, String conteudo) throws IOException {
		FileWriter abreArquivo = new FileWriter(arq, existe);
		PrintWriter escreveArq = new PrintWriter(abreArquivo);
		escreveArq.write(conteudo);
		escreveArq.flush();
		escreveArq.close();
		abreArquivo.close();
	}
	
	private String geraConteudo() {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		while (!linha.equals("fim")) {
			linha = JOptionPane.showInputDialog(null,
					"Digite a entrada","ENTRADA",
					JOptionPane.PLAIN_MESSAGE);
			if (!linha.equals("fim")) {
				buffer.append(linha+"\r\n");
			}
		}
		return buffer.toString();
	}

	@Override
	public void abreArquivo(String caminho, String arquivo) throws IOException {
		Desktop desktop = Desktop.getDesktop();
		File arq = new File(caminho, arquivo);
		if (arq.exists() && arq.isFile()) {
			desktop.open(arq);
		}
	}

}
