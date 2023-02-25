import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetCityHasCheapestInternetConnectionInteractor
import interactor.GetHighestSalaryAverageCititesNamesInteractor
import model.CityDetails
import model.CityEntity

fun main() {

    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)

    val citiesWithHighSalary = GetHighestSalaryAverageCititesNamesInteractor(dataSource).execute(10)

    val s = CityDetails(dataSource)

    val dic = mutableMapOf<Float, String>()

    val citiesAfterExclude = mutableListOf<CityEntity>()

    for(i in citiesWithHighSalary){
        if(CityDetails(dataSource).excludeNullValues(i)){
            citiesAfterExclude.add(i)
        }
    }

    var temp = Float.MIN_VALUE          // temp to store max store
    var cityEnt = citiesAfterExclude[0]
    for (i in citiesAfterExclude)            //To calculate the best city in terms of cost
    {
        val saved = 2* i.averageMonthlyNetSalaryAfterTax!! -
                minOf(i.realEstatesPrices.apartment3BedroomsInCityCentre ?: Float.MAX_VALUE,                    //
                    i.realEstatesPrices.apartment3BedroomsOutsideOfCentre ?:Float.MAX_VALUE)                // house-cost
                30 * i.foodPrices.localCheese1kg!! -
                i.foodPrices.localCheese1kg -
                4*i.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat!! -
                10*i.foodPrices.chickenFillets1kg!! -
                2*i.foodPrices.riceWhite1kg!! -
                250
        if(temp<saved){
            cityEnt = i
            temp = saved

        }

    }


    println("the most suitable city is : ${cityEnt.cityName}")


    /*val getHighestSalaryAverageCities = GetHighestSalaryAverageCititesNamesInteractor(dataSource)
    println(getHighestSalaryAverageCities.execute(limit = 10))
    printSeparationLine()

    val getCityHasCheapestInternetConnectionInteractor = GetCityHasCheapestInternetConnectionInteractor(dataSource)
    println(getCityHasCheapestInternetConnectionInteractor.execute())*/

}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}



