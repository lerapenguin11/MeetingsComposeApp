package com.example.data.responseModel.interest

class InterestsResponse : ArrayList<InterestItem>() {

    //TODO: удалить
    init {
        add(InterestItem(0, "Дизайн"))
        add(InterestItem(1, "Разработка"))
        add(InterestItem(2, "Продакт менеджмент"))
        add(InterestItem(3, "Проджект менеджмент"))
        add(InterestItem(4, "Backend"))
        add(InterestItem(5, "Frontend"))
        add(InterestItem(6, "Mobile"))
        add(InterestItem(7, "Тестирование"))
        add(InterestItem(8, "Продажи"))
        add(InterestItem(9, "Бизнес"))
        add(InterestItem(10, "Безопасность"))
        add(InterestItem(11, "Web"))
        add(InterestItem(12, "Девопс"))
        add(InterestItem(13, "Маркетинг"))
        add(InterestItem(14, "Аналитика"))
    }
}

data class InterestItem(
    val id: Int,
    val title: String
)