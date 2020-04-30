package trabalhoFuzzy;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.JFuzzyLogic;

public class executavel {
	public static void main(String[] args) throws Exception {
		String filename = "arquivo.fcl";
		String args1[] = { filename };
		FIS fis = FIS.load(filename, true);

		if (fis == null) {
			System.err.println("Erro carregando o arquivo: '" + filename + "'");
			System.exit(1);
		}

		// Get default function block
		FunctionBlock fb = fis.getFunctionBlock(null);
		
		// Set inputs
		
		double velocidade = Double.parseDouble(JOptionPane.showInputDialog("De 0 a 150 km/h, qual a velocidade média do carro?"));
		fb.setVariable("velocidade", velocidade);
		double ar_condicionado = Double.parseDouble(JOptionPane.showInputDialog("De 0 a 5, qual a potência do ar-condicionado?"));
		fb.setVariable("ar_condicionado", ar_condicionado);

		// Evaluate
		fb.evaluate();

		// Show output variable's chart
		fb.getVariable("km_por_litro").defuzzify();

		JFuzzyLogic jFuzzyLogic = new JFuzzyLogic(args1);
		jFuzzyLogic.run();
		
		// Print ruleSet
		DecimalFormat df = new DecimalFormat("####0.00");
		JOptionPane.showMessageDialog(null, "Com esses valores, temos uma média de: "+ df.format(fb.getVariable("km_por_litro").getValue())+"km/litro de gasolina. Nas janelas acima, você pode conferir a relação entre valores.");
		System.out.println(fb.getVariable("km_por_litro").toString());

	}

}
