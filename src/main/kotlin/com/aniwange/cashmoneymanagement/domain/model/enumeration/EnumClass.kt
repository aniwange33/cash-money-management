package com.aniwange.cashmoneymanagement.domain.model.enumeration

enum class  TransactionType(transactionName:String){
    TRANSFER("TRANSFER"),
    WITHDRAWAL("WITHDRAWAL")

}
enum class  ChargeType(chargeName: String){
    THREE_THOUSAND_BELOW("THREE THOUSAND BELOW"){
        override  fun calculateCharge() = 300.00
    },
    TEN_THOUSAND_BELOW("TEN THOUSAND BELOW"){
        override  fun calculateCharge() = 500.00
    },
    TEN_THOUSAND_ABOVE("TEN THOUSAND BELOW"){
        override  fun calculateCharge() = 1000.00
    };


    abstract fun calculateCharge(): Double
}