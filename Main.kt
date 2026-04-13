import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var nomeUsuario = ""
val quartos = MutableList(20) { "" }
val hotel = "HOTEL EDU PALACE"
var quantidadeEventosConfirmados = 0
var receitaEventos = 0.0

data class Reserva(
    val hospede: String,
    val quarto: Int,
    val diarias: Int,
    val total: Double
)

val reservas = mutableListOf<Reserva>()


data class HospedeCadastro(
    val nome: String,
    val dataHora: LocalDateTime
)


val hospedesCadastrados = mutableListOf<HospedeCadastro>()
const val MAX_HOSPEDES = 15





// Recepção do hotel
fun main() {
    println("Seja bem-vindo ao $hotel!")
    fazerLogininicial ()
}

// Login com senha de acesso
fun fazerLogininicial (){
    var tentativas = 0
    val senhacorreta = "2678"
    var acessoconcedido = false

    while (tentativas < 3) {
        print("Digite sua senha: ")
        val senha = readln()

        if (senha == senhacorreta) {
            acessoconcedido = true
            break
        } else {
            tentativas++
            println("Senha incorreta. Tentativa $tentativas de 3.")
        }
    }
    if (acessoconcedido) {
        print("Digite o seu nome?")
        val nome = readln()
        println("Bem-vindo $nome! É um imenso prazer ter você por aqui!")
    } else {
        println("Sistema bloqueado por excesso de tentativas.")
    }
    Menuprincipal()
}

// Menu principal
fun  Menuprincipal (){
    var opcao: Int
   do {
     println("\n --- Menu Principal--- ")
      println("1 - Reserva de quartos ")
     println("2 - Cadastro de hóspedes ")
     println("3 - Eventos ")
     println("4 - Ar -Condicionado ")
     println("5 - Abastecimento")
     println("6 - Relatórios operacionais ")
     print ("0 - sair")

       println("\n Digite sua opção: ")

     opcao = readln()?.toIntOrNull() ?: -1
       when (opcao) {
           1 -> Reservadequartos()
           2 -> Cadastrodehóspedes()
           3 -> eventos()
           4 -> arCondicionado()
           5 -> abastecimento()
           6 -> relatorios()
           7 -> println("Muito obrigado e até logo, $nomeUsuario.")
           else -> opcaoInvalida()
       }
       println()
   } while (opcao != 0)
   Reservadequartos()
}
// Reserva de quartos (Subprograma)
     fun Reservadequartos() {
    println("\nReserva de quartos - $hotel")


    println("Informe o valor da diária: ")
    val valorDiaria = readLine()
        ?.replace(",", ".")
        ?.toDoubleOrNull()


    if (valorDiaria == null || valorDiaria <= 0) {
        println("Valor inválido.")
        return
    }

    println("Informe a quantidade de diárias (1 a 30): ")
    val diarias = readLine()?.toIntOrNull()

    if (diarias == null || diarias !in 1..30) {
        println("Quantidade de diárias inválida.")
        return
    }

    println("Informe o nome completo do hóspede: ")
    val nomeHospede = readLine() ?: ""


    println(
        "Escolha um tipo de quarto:\n" +
                "S - Padrão\n" +
                "E - Executivo\n" +
                "L - Luxo"
    )

    val tipoQuarto = (readLine() ?: "").uppercase()
    val fator = when (tipoQuarto) {
        "S" -> 1.0
        "E" -> 1.35
        "L" -> 1.65
        else -> {
            println("Tipo de quarto inválido.")
            return
        }
    }
    val nomeTipo = when (tipoQuarto) {
        "S" -> "Padrão"
        "E" -> "Executivo"
        else -> "Luxo"
    }
    var numeroQuarto: Int
    while (true) {
        print("Escolha um quarto (1 a 20): ")
        numeroQuarto = readLine()?.toIntOrNull() ?: -1

        if (numeroQuarto !in 1..20) {
            println("Número de quarto inválido.")
            continue
        }

        if (quartos[numeroQuarto - 1].isNotEmpty()) {
            println("Quarto ocupado. Quartos livres:")
            quartos.forEachIndexed { index, q ->
                if (q.isEmpty()) print("${index + 1} ")
            }
            println()
            continue
        }
        break
    }
    val subtotal = valorDiaria * diarias * fator
    val taxa = subtotal * 0.10
    val total = subtotal + taxa

    println("\nResumo da Reserva:")
    println("Hóspede: $nomeHospede")
    println("Quarto: $numeroQuarto ($nomeTipo)")
    println("Subtotal: R$ %.2f".format(subtotal))
    println("Taxa de serviço (10%%): R$ %.2f".format(taxa))
    println("Total: R$ %.2f".format(total))

    print("\nDeseja confirmar a reserva? (S/N): ")
    val confirmacao = (readLine() ?: "").uppercase()

    if (confirmacao == "S") {
        quartos[numeroQuarto - 1] = nomeHospede
        reservas.add(Reserva(nomeHospede, numeroQuarto, diarias, total))
        println("✅ Reserva realizada com sucesso!")
        exibirMapaQuartos()
    } else {
        println("Reserva cancelada.")
    }
}
fun exibirMapaQuartos() {
    println("\nMapa de Quartos:")
    quartos.forEachIndexed { index, q ->
        val status = if (q.isEmpty()) "L" else "O"
        print("[$status] ")
        if ((index + 1) % 5 == 0) println()
    }
}




// Cadastro de hóspedes

fun Cadastrodehóspedes () {


        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        var opcao: Int

        do {
            println("\nCadastro de Hóspedes - $hotel")
            println("Escolha uma opção: ")
            println(
                "1-Cadastrar \n " +
                        "2-Pesquisar exato\n " +
                        "3-Pesquisar prefixo\n" +
                        " 4-Listar\n " +
                        "5-Atualizar\n " +
                        "6-Remover\n " +
                        "7-Voltar\n"
            )

            opcao = readLine()?.toIntOrNull() ?: -1

            when (opcao) {

                1 -> {
                    if (hospedesCadastrados.size >= MAX_HOSPEDES) {
                        println("Máximo de cadastros atingido")
                    } else {
                        print("Nome do hóspede: ")
                        val nome = readLine() ?: ""
                        if (hospedesCadastrados.any { it.nome.equals(nome, true) }) {
                            println("Hóspede já cadastrado")
                        } else {
                            hospedesCadastrados.add(HospedeCadastro(nome, LocalDateTime.now()))
                            println("Operação realizada com sucesso")
                        }
                    }
                }

                2 -> {
                    print("Nome: ")
                    val nome = readLine() ?: ""
                    if (hospedesCadastrados.any { it.nome == nome }) {
                        println("Hospede $nome foi encontrado")
                    } else {
                        println("Hóspede não encontrado")
                    }
                }

                3 -> {
                    print("Prefixo: ")
                    val prefixo = readLine() ?: ""
                    val encontrados = hospedesCadastrados.filter { it.nome.startsWith(prefixo, true) }
                    if (encontrados.isEmpty()) {
                        println("Hóspede não encontrado")
                    } else {
                        encontrados.forEachIndexed { index, h ->
                            println("[${index + 1}] ${h.nome}")
                        }
                    }
                }

                4 -> {
                    val lista = hospedesCadastrados.sortedBy { it.nome.lowercase() }
                    lista.forEachIndexed { index, h ->
                        println("[${index + 1}] ${h.nome} - ${h.dataHora.format(formatter)}")
                    }
                }

                5 -> {
                    hospedesCadastrados.forEachIndexed { index, h ->
                        println("[${index + 1}] ${h.nome}")
                    }
                    print("Índice: ")
                    val i = (readLine()?.toIntOrNull() ?: 0) - 1
                    if (i in hospedesCadastrados.indices) {
                        print("Novo nome: ")
                        val novoNome = readLine() ?: ""
                        hospedesCadastrados[i] = hospedesCadastrados[i].copy(nome = novoNome)
                        println("Operação realizada com sucesso")
                    } else println("Hóspede não encontrado")
                }

                6 -> {
                    hospedesCadastrados.forEachIndexed { index, h ->
                        println("[${index + 1}] ${h.nome}")
                    }
                    print("Índice: ")
                    val i = (readLine()?.toIntOrNull() ?: 0) - 1
                    if (i in hospedesCadastrados.indices) {
                        hospedesCadastrados.removeAt(i)
                        println("Operação realizada com sucesso")
                    } else println("Hóspede não encontrado")
                }
            }
        } while (opcao != 7)
    }


    fun eventos() {

        println("Eventos - $hotel")

        print("Quantos convidados? ")
        val convidados = readLine()?.toIntOrNull()

        if (convidados == null || convidados < 0 || convidados > 350) {
            println("Número de convidados inválidos.")
            return
        }
        val auditorio: String
        val cadeirasExtras: Int

        when {
            convidados <= 150 -> {
                auditorio = "Laranja"
                cadeirasExtras = 0
            }

            convidados <= 220 -> {
                auditorio = "Laranja"
                cadeirasExtras = convidados - 150
            }

            else -> {
                auditorio = "Colorado"
                cadeirasExtras = 0
            }
        }

        println(
            if (auditorio == "Laranja" && cadeirasExtras > 0)
                "Auditório selecionado: Laranja ($cadeirasExtras cadeiras adicionais)"
            else
                "Auditório selecionado: $auditorio"
        )

        println("\nPara qual dia?: ")
        val dia = readLine() ?: ""

        println("Hora inicial: ")
        val horaInicial = readLine()?.toIntOrNull()

        println("Qual a duração? (de 1 à 12 horas): ")
        val duracao = readLine()?.toIntOrNull()

        if (horaInicial == null || duracao == null || duracao !in 1..12) {
            println("Dados de horário inválidos.")
            return
        }

        val horaFinal = horaInicial + duracao

        val janelaValida = when (dia) {
            "segunda", "terca", "quarta", "quinta", "sexta" ->
                horaInicial in 7..22 && horaFinal <= 23

            "sabado", "domingo" ->
                horaInicial in 7..14 && horaFinal <= 15

            else -> false
        }

        if (!janelaValida) {
            println("Auditório indisponível.")
            return
        }

        print("Empresa: ")
        val empresa = readLine() ?: ""

        println("Status: Auditório reservado.")

        val baseGarcons = kotlin.math.ceil(convidados / 12.0).toInt()
        val reforcoGarcons = duracao / 2
        val totalGarcons = baseGarcons + reforcoGarcons
        val custoGarcons = totalGarcons * duracao * 10.5

        println("\nGarçons necessários: $totalGarcons")
        println("Custo com garçons: R$ %.2f".format(custoGarcons))


        val litrosCafe = convidados * 0.2
        val litrosAgua = convidados * 0.5
        val salgados = convidados * 7
        val custoCafe = litrosCafe * 0.8
        val custoAgua = litrosAgua * 0.4
        val custoSalgados = (salgados / 100.0) * 34.0
        val custoBuffet = custoCafe + custoAgua + custoSalgados

        println("\nBuffet:")
        println("Café: %.1f L".format(litrosCafe))
        println("Água: %.1f L".format(litrosAgua))
        println("Salgados: $salgados un")
        println("Custo buffet: R$ %.2f".format(custoBuffet))

        val totalEvento = custoGarcons + custoBuffet

        println("\nResumo do Evento:")
        println("Auditório: $auditorio")
        println("Empresa: $empresa")
        println("Dia: $dia")
        println("Horário: ${horaInicial}h às ${horaFinal}h")
        println("Convidados: $convidados")
        println("Duração: $duracao horas")
        println("Custo garçons: R$ %.2f".format(custoGarcons))
        println("Custo buffet: R$ %.2f".format(custoBuffet))
        println("Total do evento: R$ %.2f".format(totalEvento))

        print("\nConfirmar reserva? (S/N): ")
        val confirmar = (readLine() ?: "").uppercase()

        if (confirmar == "S") {
            quantidadeEventosConfirmados++
            receitaEventos += totalEvento
            println("Reserva efetuada com sucesso.")
        } else {
            println("Reserva não efetuada.")
        }


    }


    fun arCondicionado() {

        println("Ar-Condicionado - $hotel")

        var menorValor = Double.MAX_VALUE
        var maiorValor = 0.0
        var empresaMenor = ""
        var empresaMaior = ""

        do {
            print("Empresa: ")
            val empresa = readLine() ?: ""

            print("Valor por aparelho: ")
            val valorAparelho = readLine()
                ?.replace(",", ".")
                ?.toDoubleOrNull() ?: 0.0

            print("Quantidade de aparelhos: ")
            val quantidade = readLine()?.toIntOrNull() ?: 0

            print("Desconto (%): ")
            val descontoPercentual = readLine()
                ?.replace(",", ".")
                ?.toDoubleOrNull() ?: 0.0

            print("Mínimo para desconto: ")
            val minimo = readLine()?.toIntOrNull() ?: 0

            print("Deslocamento: ")
            val deslocamento = readLine()
                ?.replace(",", ".")
                ?.toDoubleOrNull() ?: 0.0

            val bruto = valorAparelho * quantidade

            val desconto = if (quantidade >= minimo)
                bruto * (descontoPercentual / 100)
            else
                0.0

            val total = bruto - desconto + deslocamento

            println("Total: R$ %.2f".format(total))
            println("O serviço de $empresa custará R$ %.2f\n".format(total))

            if (total < menorValor) {
                menorValor = total
                empresaMenor = empresa
            }

            if (total > maiorValor) {
                maiorValor = total
                empresaMaior = empresa
            }

            print("Deseja informar novos dados, $empresa? (S/N): ")
            val continuar = (readLine() ?: "").uppercase()

            if (continuar != "S") break

        } while (true)

        val diferencaPercentual =
            if (maiorValor > 0)
                ((maiorValor - menorValor) / maiorValor) * 100
            else 0.0

        println("\nResumo final:")
        println("Melhor orçamento: $empresaMenor — R$ %.2f".format(menorValor))
        println("Pior orçamento: $empresaMaior — R$ %.2f".format(maiorValor))
        println("Diferença percentual: %.2f%%".format(diferencaPercentual))
    }


    fun abastecimento() {

        println("Abastecimento - $hotel")

        val tanque = 42.0

        println("Wayne Oil -> Preço do Álcool: ")
        val alcoolWayne = readLine()?.replace(",", ".")?.toDoubleOrNull()

        println("Wayne Oil -> Preço da Gasolina: ")
        val gasolinaWayne = readLine()?.replace(",", ".")?.toDoubleOrNull()

        println("Stark Petrol -> Preço do Álcool: ")
        val alcoolStark = readLine()?.replace(",", ".")?.toDoubleOrNull()

        println("Stark Petrol -> Preço da Gasolina: ")
        val gasolinaStark = readLine()?.replace(",", ".")?.toDoubleOrNull()

        if (
            alcoolWayne == null || gasolinaWayne == null ||
            alcoolStark == null || gasolinaStark == null
        ) {
            println("Valores inválidos.")
            return
        }


        fun melhorCombustivel(alcool: Double, gasolina: Double): Pair<String, Double> {
            return if (alcool <= 0.7 * gasolina) {
                "Álcool" to alcool * tanque
            } else {
                "Gasolina" to gasolina * tanque
            }
        }

        val (combWayne, totalWayne) = melhorCombustivel(alcoolWayne, gasolinaWayne)
        val (combStark, totalStark) = melhorCombustivel(alcoolStark, gasolinaStark)

        println(
            "\nWayne Oil -> Álcool: %.2f | Gasolina: %.2f"
                .format(alcoolWayne, gasolinaWayne)
        )
        println(
            "Stark Petrol -> Álcool: %.2f | Gasolina: %.2f\n"
                .format(alcoolStark, gasolinaStark)
        )

        println(
            "Wayne Oil: melhor opção = $combWayne | Total (42L) = R$ %.2f"
                .format(totalWayne)
        )
        println(
            "Stark Petrol: melhor opção = $combStark | Total (42L) = R$ %.2f"
                .format(totalStark)
        )

        val maisBarato: String
        val combustivelFinal: String

        if (totalWayne < totalStark) {
            maisBarato = "Wayne Oil"
            combustivelFinal = combWayne
        } else {
            maisBarato = "Stark Petrol"
            combustivelFinal = combStark
        }

        println(
            "\n$nomeUsuario, é mais barato abastecer com " +
                    "$combustivelFinal no posto $maisBarato."
        )
    }


    fun relatorios() {

        println("\nRELATÓRIOS OPERACIONAIS - $hotel")
        println("=".repeat(60))

        val quartosOcupados = quartos.count { it.isNotEmpty() }
        val taxaCarregamento = (quartosOcupados / 20.0) * 100

        val totalReservas = reservas.size
        val receitaReservas = reservas.sumOf { it.total }

        val totalHospedes = hospedesCadastrados.size
        val totalEventos = quantidadeEventosConfirmados

        val receitaTotal = receitaReservas + receitaEventos

        println(
            """
        |ITEM                              | VALOR
        |----------------------------------|-----------------------
        |Reservas de quartos confirmadas   | $totalReservas
        |Quartos ocupados                  | $quartosOcupados / 20
        |Taxa de carregamento              | ${"%.2f".format(taxaCarregamento)}%
        |Hóspedes cadastrados              | $totalHospedes
        |Eventos confirmados               | $totalEventos
        |----------------------------------|-----------------------
        |Receita com reservas (R$)         | ${"%.2f".format(receitaReservas)}
        |Receita com eventos (R$)          | ${"%.2f".format(receitaEventos)}
        |----------------------------------|-----------------------
        |RECEITA TOTAL (R$)                | ${"%.2f".format(receitaTotal)}
        """.trimIndent()
        )
    }


    fun opcaoInvalida() {
        println("Opção inválida!")
        Menuprincipal()
    }







