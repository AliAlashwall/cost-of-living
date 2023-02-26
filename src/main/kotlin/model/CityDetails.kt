package model

class CityDetails {

  fun calculateSavedMoney(city: CityEntity): Float              //to calculate the savings money for this family per month
  {
      return  2* city.averageMonthlyNetSalaryAfterTax!! -
              minOf(city.realEstatesPrices.apartment3BedroomsInCityCentre ?: Float.MAX_VALUE,                    //
                  city.realEstatesPrices.apartment3BedroomsOutsideOfCentre ?:Float.MAX_VALUE)  -            // house-cost
              30 * city.foodPrices.localCheese1kg!! -
              city.foodPrices.localCheese1kg -
              4*city.foodPrices.beefRound1kgOrEquivalentBackLegRedMeat!! -
              10*city.foodPrices.chickenFillets1kg!! -
              2*city.foodPrices.riceWhite1kg!! -
              250

  }

    fun excludeIncompleteData(city: CityEntity): Boolean        // To make sure there is nothing with null
    {
        return city.run {
                    averageMonthlyNetSalaryAfterTax != null &&
                    realEstatesPrices.run {
                        (apartment3BedroomsInCityCentre != null || apartment3BedroomsOutsideOfCentre != null)}
                    && foodPrices.run {
                                        chickenFillets1kg != null &&
                                        loafOfFreshWhiteBread500g != null &&
                                        localCheese1kg != null &&
                                        riceWhite1kg != null &&
                                        beefRound1kgOrEquivalentBackLegRedMeat != null
                                        }
        }
    }
}