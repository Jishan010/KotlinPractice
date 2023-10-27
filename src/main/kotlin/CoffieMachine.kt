import java.util.*

fun main() {

    val resources = Resources(30, 30, 30)
    while (true) {
        try {
            println("Welcome to the Coffee Machine!")
            println("Menu:")
            val menuMap: MutableMap<Int, Menu> = displayMenu()
            print("Enter the number of the coffee you'd like (0-2), or type 'exit' to quit: ")
            val userInput = readln()

            if (userInput == "exit") {
                break
            }

            val selectedMenu = menuMap[userInput.toInt()]
            if (selectedMenu == null) {
                println("Invalid coffee choice. Please choose between 0-2.")
                continue
            }
            println(userInput)
            val checkAvailability = resources.getMenuAvailability(selectedMenu)
            if (checkAvailability) {
                println("Making ${selectedMenu.name}.....")
                Thread.sleep(3000)
                println("Here is your ${selectedMenu.name}. Enjoy!")
                println()
                println()
                Thread.sleep(3000)
            } else {
                Thread.sleep(3000)
                println("${selectedMenu?.name} Cannot be made ! Insufficient Resources")
                println("Do you want to refill resources ? type 'yes' or 'exit' to quit: ")
                if (readln() == "exit") {
                    break
                } else {
                    //refill
                    resources.refill()
                }
            }

        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a valid integer.")
        }
    }
}

private fun displayMenu(): MutableMap<Int, Menu> {

    val latte = Menu(0, "Latte", 4, 10, 10, 10)
    val espresso = Menu(1, "Espresso", 7, 20, 20, 20)
    val cappuccino = Menu(2, "Cappuccino", 6, 30, 30, 30)

    val menuMap: MutableMap<Int, Menu> = mutableMapOf(
        0 to latte, 1 to espresso, 2 to cappuccino
    )

    for ((_, item) in menuMap) {
        println("${item.id} - ${item.name} - $${item.price}")
    }
    return menuMap
}

data class Menu(
    val id: Int,
    val name: String,
    val price: Int,
    val requiredMilk: Int,
    val requiredCoffeeBeans: Int,
    val requiredWater: Int,
)

class Resources(private var water: Int, private var milk: Int, private var coffeeBeans: Int) {
    init {
        if (water > 30 || milk > 30 || coffeeBeans > 30) {
            println("Max limit exceeded")
            refill();
        }
    }

    fun refill() {
        water = 30
        milk = 30
        coffeeBeans = 30
    }

    fun getMenuAvailability(menuItem: Menu): Boolean {
        if (water < menuItem.requiredWater || milk < menuItem.requiredMilk || coffeeBeans < menuItem.requiredCoffeeBeans) {
            return false
        }
        milk -= menuItem.requiredMilk
        water -= menuItem.requiredWater
        coffeeBeans -= menuItem.requiredCoffeeBeans
        return true
    }
}
