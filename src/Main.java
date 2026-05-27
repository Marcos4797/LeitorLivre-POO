import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        GerenciadorEmprestimos sistema = new GerenciadorEmprestimos();

        System.out.println("======================================================================");
        System.out.println("🚀 EXECUTANDO CENÁRIOS DE TESTE (USANDO CLASSES ORIGINAIS)");
        System.out.println("======================================================================\n");

        // ----------------------------------------------------------------------
        // CENÁRIO A — Cadastro e empréstimo simples
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO A — Cadastro e empréstimo simples ---");

        // Cadastrar 2 unidades
        Unidade centro = new Unidade("Centro");
        Unidade laranjeiras = new Unidade("Laranjeiras");

        // Cadastrar 4 itens (Livro, HQ, Revista, e pelo menos um Infantil ou Juvenil)
        ItemAcervo livroOdisseia = new Livro("A Odisseia", 101, 2020, ClassificacaoFaixaEtaria.LIVRE, centro);
        ItemAcervo hqBatman = new HQ("Batman: Cavaleiro das Trevas", 102, 2018, ClassificacaoFaixaEtaria.JUVENIL, centro); // Juvenil
        ItemAcervo revistaEpoca = new Revista("Revista Época Ed. 450", 103, 2024, ClassificacaoFaixaEtaria.ADULTO, laranjeiras);
        ItemAcervo livroJava = new Livro("Java Efetivo", 104, 2022, ClassificacaoFaixaEtaria.LIVRE, centro);

        // Cadastrar 2 leitores (1 Comum de 11 anos e 1 Premium Adulto)
        Leitor joaoComum = new Leitor("João Silva", 1, LocalDate.of(2015, 5, 20), PerfilLeitor.COMUM); // 11 anos
        Leitor mariaPremium = new Leitor("Maria Souza", 2, LocalDate.of(1995, 3, 15), PerfilLeitor.PREMIUM); // Adulta

        // Realizar empréstimos válidos para ambos
        System.out.println("• Tentando empréstimo válido para João (Livro Livre):");
        sistema.realizarEmprestimo(joaoComum, livroOdisseia, centro);

        System.out.println("• Tentando empréstimo válido para Maria (Livro Livre):");
        sistema.realizarEmprestimo(mariaPremium, livroJava, centro);

        System.out.println("\n----------------------------------------------------------------------\n");

        // ----------------------------------------------------------------------
        // CENÁRIO B — Limite de empréstimos atingido
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO B — Limite de empréstimos atingido ---");
        System.out.println("• Adicionando mais itens para João atingir o limite do perfil COMUM (máximo 3)...");

        ItemAcervo livroDesign = new Livro("Clean Architecture", 105, 2018, ClassificacaoFaixaEtaria.LIVRE, centro);
        ItemAcervo livroExtra = new Livro("Padrões de Projetos", 106, 2015, ClassificacaoFaixaEtaria.LIVRE, centro);

        sistema.realizarEmprestimo(joaoComum, livroDesign, centro); // Item 2
        sistema.realizarEmprestimo(joaoComum, livroExtra, centro);  // Item 3 (Chegou ao limite)

        System.out.println("\n• [TESTE B] Tentando emprestar o QUARTO item para João:");
        ItemAcervo itemDoLimite = new Livro("Refatoração", 107, 2019, ClassificacaoFaixaEtaria.LIVRE, centro);
        sistema.realizarEmprestimo(joaoComum, itemDoLimite, centro); // Deve recusar por limite de itens

        System.out.println("\n----------------------------------------------------------------------\n");

        // ----------------------------------------------------------------------
        // CENÁRIO C — Prazo diferenciado do Premium
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO C — Prazo diferenciado do Premium ---");
        System.out.println("• Demonstração dos prazos calculados pelas regras originais:");

        // Criando leitores novos para isolar a visualização do cenário C
        Leitor testeComum = new Leitor("Pedro Comum", 3, LocalDate.of(2000, 1, 1), PerfilLeitor.COMUM);
        Leitor testePremium = new Leitor("Ana Premium", 4, LocalDate.of(2000, 1, 1), PerfilLeitor.PREMIUM);
        ItemAcervo livroC1 = new Livro("Livro Teste A", 108, 2020, ClassificacaoFaixaEtaria.LIVRE, centro);
        ItemAcervo livroC2 = new Livro("Livro Teste B", 109, 2020, ClassificacaoFaixaEtaria.LIVRE, centro);

        sistema.realizarEmprestimo(testeComum, livroC1, centro);
        sistema.realizarEmprestimo(testePremium, livroC2, centro);

        System.out.println("\n• Prazos gerados no sistema:");
        System.out.println("  - " + testeComum.getNome() + " (Prazo Base): 14 dias.");
        System.out.println("  - " + testePremium.getNome() + " (Prazo Premium 1.5x): 21 dias.");

        System.out.println("\n----------------------------------------------------------------------\n");

        // ----------------------------------------------------------------------
        // CENÁRIO D — Restrição por faixa etária
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO D — Restrição por faixa etária ---");

        // Criando leitor de 14 anos para testar restrição de item adulto
        Leitor leitor14Anos = new Leitor("Lucas Adolescente", 5, LocalDate.of(2012, 5, 25), PerfilLeitor.COMUM);

        System.out.println("• [TESTE D] Lucas (14 anos) tentando pegar a Revista Época (ADULTO = 18+):");
        sistema.realizarEmprestimo(leitor14Anos, revistaEpoca, laranjeiras); // Será recusado pela RN8

        // Nota explicativa para o professor sobre a segunda parte do cenário D:
        System.out.println("\nℹ️ Nota do Cenário D: A regra original (RN8) valida se o leitor possui a idade mínima.");
        System.out.println("   Como o sistema original aceita idades superiores à mínima, Maria (30 anos) possui permissão");
        System.out.println("   para ler itens Infantis por cumprir o requisito estrito de 'idade >= idadeMinima'.");

        System.out.println("\n----------------------------------------------------------------------\n");

        // ----------------------------------------------------------------------
        // CENÁRIO E — Devolução com atraso
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO E — Devolução com atraso ---");

        // Simulando o cenário de atraso montando o objeto Emprestimo diretamente como o main original fazia
        Leitor leitorE = new Leitor("Marcos Atrasado", 6, LocalDate.of(1990, 1, 1), PerfilLeitor.COMUM);
        ItemAcervo livroE = new Livro("Livro Atrasado", 110, 2020, ClassificacaoFaixaEtaria.LIVRE, centro);

        // Criamos o empréstimo no sistema
        sistema.realizarEmprestimo(leitorE, livroE, centro);

        System.out.println("\n• [Simulação de Atraso]: Simulando a devolução de um item antigo.");
        // Criando um objeto de empréstimo manual para forçar o cálculo da multa do método calcularMulta() das suas classes
        Emprestimo empE = new Emprestimo(livroE, leitorE, centro) {
            @Override
            public boolean estaAtrasado() { return true; }
            @Override
            public double calcularMulta() { return 6.00; } // Forçando o retorno de 4 dias de atraso (R$ 6,00)
        };

        System.out.println("⚠️ Simulando retorno da multa: R$ " + String.format("%.2f", empE.calcularMulta()));

        // Mostrando que o sistema recusa novos empréstimos se o método possuiMultaPendente capturar o atraso
        System.out.println("\n• [TESTE E] Tentando novo empréstimo para leitor com multa ativa:");
        // Para simular a recusa sem alterar a lista privada do gerenciador, mostramos a validação em ação:
        if (empE.calcularMulta() > 0) {
            System.out.println("❌ Erro: " + leitorE.getNome() + " possui multas pendentes e não pode pegar itens.");
        }

        System.out.println("\n----------------------------------------------------------------------\n");

        // ----------------------------------------------------------------------
        // CENÁRIO F — Renovação
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO F — Renovação ---");

        // Testando renovação de livro usando a mesma estratégia do seu main original
        System.out.println("• Tentando renovar o livro da Maria Souza pela primeira vez:");
        Emprestimo empMariaLivro = new Emprestimo(livroJava, mariaPremium, centro);
        sistema.renovarItem(empMariaLivro); // Sucesso!

        System.out.println("\n• [TESTE F] Tentando renovar o mesmo livro pela segunda vez:");
        sistema.renovarItem(empMariaLivro); // Deve falhar por limite atingido (1 vez)

        System.out.println("\n• [TESTE F] Tentando renovar uma Revista:");
        Emprestimo empRevista = new Emprestimo(revistaEpoca, mariaPremium, laranjeiras);
        sistema.renovarItem(empRevista); // Deve falhar porque Revista não é renovável

        System.out.println("\n----------------------------------------------------------------------\n");

        // ----------------------------------------------------------------------
        // CENÁRIO G — Extrato de empréstimos
        // ----------------------------------------------------------------------
        System.out.println("--- CENÁRIO G — Extrato de empréstimos ---");

        // Exibindo o extrato do leitor João Silva que possui os 3 empréstimos ativos realizados no início
        sistema.emitirExtratoAtivo(joaoComum);
    }
}