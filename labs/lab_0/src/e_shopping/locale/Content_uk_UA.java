package e_shopping.locale;

import java.util.ListResourceBundle;

public class Content_uk_UA extends ListResourceBundle {
	private Object[][] content =
		{
				{"pageName", "Продуктовий онлайн-магазин"},
				{"addButton", "Додати"},
				{"editButton", "Змінити"},
				{"deleteButton", "Видалити"},
				{"createButton", "Створити"},
				{"errorPhone", "Номер телефону має містити тільки цифри та мати довжину в 10 символів"},
				{"phone", "Номер телефону"},
				{"userPhone", "Номер телефону користувача"},
				{"firstName", "Ім'я"},
				{"lastName", "Прізвище"},
				{"password", "Пароль"},
				{"login", "Логін"},
				{"loginButton", "Увійти"},
				{"signUp", "Зареєструватися"},
				{"signIn", "Увійти"},
				{"buy", "Купити"},
				{"hello", "Привіт"},
				{"products", "Продукти"},
				{"basket", "Корзина"},
				{"orders", "Замовлення"},
				{"wasPayed", "Оплачено"},
				{"yes", "Так"},
				{"no", "Ні"},
				{"emptyOrders", "Замовлень немає"},
				{"emptyProducts", "Порожня корзина"},
				{"users", "Користувачі"},
				{"emptyUsers", "Немає користувачів"},
				{"allOrders", "Всі замовлення"},
				{"unpayedOrders", "Неоплачені замовлення"},
				{"allUsers", "Всі користувачі"},
				{"userUnOrders", "Користувачі з неоплаченими замовленнями"},
				{"blacklist", "Чорний список"},
				{"cancel", "Відмінити"},
				{"submit", "Підтвердити"},
				{"rememberMe", "Запам'ятати"},
				{"nameDefault", "Назва"},
				{"nameUa", "Назва"},
				{"nameEn", "Назва(англійською)"},
				{"price", "Ціна"},
				{"amount", "Кількість"},
				{"date", "Дата"},
				{"amountUnO", "Кількість неоплачених замовлень"},
				{"sumUnO", "Сума неоплачених замовлень"},
				{"inBlacklist", "В чорному списку"},
				{"addBlacklist", "Додати до чорного списку"},
				{"removeBlacklist", "Видалити з чорного списку"},
				{"alreadyThere", "Вже там"},
				{"changeAmount", "Змінити кількість(якщо 0 - видалення)"},
				{"order", "Замовити"},
				{"sum", "Сума"},
				{"setPayed", "Позначити оплаченим"},
				{"message", "Повідомлення"},
				{"blackListMessage", "Ви в чорному списку. Оплатіть минулі замовлення перед додаванням нових."},
				{"change", "Змінити"},
				{"existedUser", "Користувач з введеним телефоном вже зареєстрований."}
				
		};

	@Override
	protected Object[][] getContents() {
		return content;
	}
}
