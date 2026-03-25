
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
        val hotel = "HOTEL EDU PALACE"
        val nome = readln()
        println("Bem-vindo ao $hotel! É um imenso prazer ter você por aqui!")
    } else {
        println("Sistema bloqueado por excesso de tentativas.")
        inicio()
    }
}

// Menu principal
fun inicio (){
    var opcao: Int
   do {
     println("\n --- Menu Principal--- ")
      println("1 - Reserva de quartos ")
     println("2 - Cadatro de hóspedes ")
     println("3 - Eventos ")
     println("4 - Ar - Condicionado ")
     println("5 - Abastecimento")
     println("6 - Relatórios operacionais ")
     print ("0 - sair")

     opcao = readln()?.toIntOrNull() ?: -1

       when (opcao) {
           1 -> println("Você escolheu cadastrar usuário.")
           2 -> println("Você escolheu listar usuários.")
           3 -> println("Você escolheu excluir usuário.")
           0 -> println("Saindo do sistema...")
           else -> println("Opção inválida, tente novamente.")
       }
       println()
   } while (opcao != 0)
}










