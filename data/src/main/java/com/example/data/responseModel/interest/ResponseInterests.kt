package com.example.data.responseModel.interest

class RequestInterests : ArrayList<InterestItem>() {


    //TODO: удалить
    init {
        add(InterestItem(1, "Дизайн"))
        add(InterestItem(2, "Разработка"))
        add(InterestItem(3, "Продакт менеджмент"))
        add(InterestItem(4, "Проджект менеджмент"))
        add(InterestItem(5, "Backend"))
        add(InterestItem(6, "Frontend"))
        add(InterestItem(7, "Mobile"))
        add(InterestItem(8, "Тестирование"))
        add(InterestItem(9, "Продажи"))
        add(InterestItem(10, "Бизнес"))
        add(InterestItem(11, "Безопасность"))
        add(InterestItem(12, "Web"))
        add(InterestItem(13, "Девопс"))
        add(InterestItem(14, "Маркетинг"))
        add(InterestItem(15, "Аналитика"))
    }
}

data class InterestItem(
    val id: Int,
    val title: String
)