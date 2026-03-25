
// Recepção do hotel
fun main() {
    println("---HOTEL EDU PALACE--- ")
    val hotel = "HOTEL EDU PALACE"
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
      println("1 - Reservadequartos ")
     println("2 - Cadatro de hóspedes ")
     println("3 - Eventos ")
     println("4 - Ar - Condicionado ")
     println("5 - Abastecimento")
     println("6 - Relatórios operacionais ")
     print ("0 - sair")

       println("\n Digite sua opção: ")

     opcao = readln()?.toIntOrNull() ?: -1
       when (opcao) {
        1 -> println("Você escolheu reservar quartos")
           2 ->  println("Você escolheu cadastrar hóspedes")
           3 -> println("Você escolheu eventos")
               4-> println("Você escolheu ar-condicionado")
                   5 -> println("Você escolheu abastecer")
                       6 -> println("Você escolheu ver os relatórios operacionais")
           0 -> println("Saindo do sistema...")
           else -> println("Opção inválida, tente novamente.")
       }
       println()
   } while (opcao != 0)
   Reservadequartos()
}

     // Reserva de quartos (Subprograma)
     fun  Reservadequartos() {
         println("--- [Reservas] --- ")
print ("Informe o valor da diária: ")
         val Valordadiária = readln().toDouble() ?: 0.0
         print ("Informe a quantidade de diárias (1-20) : ")
         val dias = readln().toIntOrNull() ?:0

         if (Valordadiária <= 0 || dias !in 1..30)
         println("Valor inválido")
        return

     }

    print ("Informe o nome do hóspede:")
        val nomeH = readln
print ("Tipo de quarto: S- suíte| D- deluxe| E- Executiva: ")
        val fator = when (readln().uppercase()){
            "s"
            "L"
            else 
        }




