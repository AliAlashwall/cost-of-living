import dataSource.CsvDataSource
import dataSource.utils.CsvParser
import interactor.CostOfLivingDataSource
import interactor.GetHighestSalaryAverageCititesNamesInteractor
import model.CityDetails
import model.CityEntity

fun main() {

    val csvParser = CsvParser()
    val dataSource: CostOfLivingDataSource = CsvDataSource(csvParser)
    val citiesWithHighSalary = GetHighestSalaryAverageCititesNamesInteractor(dataSource).execute(10)
    val citiesAfterExclude = mutableListOf<CityEntity>()

    for(i in citiesWithHighSalary){
        if(CityDetails().excludeIncompleteData(i)){
            citiesAfterExclude.add(i)
        }
    }

    var temp = Float.MIN_VALUE
    var cityEnt = citiesAfterExclude[0]

    for (i in citiesAfterExclude)            //To calculate the best city in terms of cost
    {
        val saved = CityDetails().calculateSavedMoney(i)

        if(temp < saved){
            cityEnt = i
            temp = saved
        }

    }

    println("The most suitable city is : ${cityEnt.cityName}")
    printSeparationLine()
}
private fun printSeparationLine(){
    print("\n_______________________________\n")
}
