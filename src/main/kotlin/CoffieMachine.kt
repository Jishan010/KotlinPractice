fun main() {
    val menuMap: MutableMap<Int, Menu> = displayMenu()
    val resources = Resources();
    while (true) {
        try {
            print("Place your order: ")
            val userInput = readln().toInt()

            val selectedMenu = menuMap[userInput];

            println("You have ordered: ${selectedMenu?.id} - ${selectedMenu?.name} - ${selectedMenu?.price}")
            val checkAvailability = selectedMenu?.let { resources.getMenuAvailability(it) }
            println(checkAvailability)

        } catch (e: NumberFormatException) {
            println("Invalid input. Please enter a valid integer.")
        }
    }
}

private fun displayMenu(): MutableMap<Int, Menu> {

    val latte = Menu(1, "Latte", 5, 10, 10, 10)
    val espresso = Menu(2, "Espresso", 20, 20, 20, 20)
    val cappuccino = Menu(3, "Cappuccino", 30, 30, 30, 30)

    val menuMap: MutableMap<Int, Menu> = mutableMapOf(
        1 to latte, 2 to espresso, 3 to cappuccino
    )

    for ((_, item) in menuMap) {
        println("${item.id} - ${item.name} - ${item.price}")
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

class Resources {
    private var water: Int = 50
    private var milk: Int = 50
    private var coffeeBeans: Int = 50

    fun getMenuAvailability(menuItem: Menu): String {
        if (water < menuItem.requiredWater || milk < menuItem.requiredMilk || coffeeBeans < menuItem.requiredCoffeeBeans) {

            return "No sufficient Items , Sorry go  somewhere else !"
        }
        milk -= menuItem.requiredMilk
        water -= menuItem.requiredWater
        coffeeBeans -= menuItem.requiredCoffeeBeans
        return "Your order is ready"
    }
}
