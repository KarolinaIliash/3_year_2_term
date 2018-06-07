package e_shopping.locale;

import java.util.ListResourceBundle;

public class Content extends ListResourceBundle{

	private Object[][] content =
			{
					{"pageName", "Products e-shop"},
					{"addButton", "Add"},
					{"editButton", "Edit"},
					{"deleteButton", "Delete"},
					{"createButton", "Create"},
					{"errorPhone", "Phone should consists only from digits and have length 10"},
					{"phone", "Phone"},
					{"userPhone", "User phone"},
					{"firstName", "First Name"},
					{"lastName", "Last Name"},
					{"password", "Password"},
					{"login", "Login"},
					{"loginButton", "Login"},
					{"signUp", "Sign Up"},
					{"signIn", "Sign in"},
					{"buy", "Buy"},
					{"hello", "Hello"},
					{"products", "Products"},
					{"basket", "Basket"},
					{"orders", "Orders"},
					{"wasPayed", "Was payed"},
					{"yes", "Yes"},
					{"no", "No"},
					{"emptyOrders", "There is no order"},
					{"emptyProducts", "There is no product in your basket"},
					{"users", "Users"},
					{"emptyUsers", "There is no user"},
					{"allOrders", "All orders"},
					{"unpayedOrders", "Unpayed orders"},
					{"allUsers", "All users"},
					{"userUnOrders", "Users with unpayed orders"},
					{"blacklist", "Black list"},
					{"cancel", "Cancel"},
					{"submit", "Submit"},
					{"rememberMe", "Remember me"},
					{"nameDefault", "Name"},
					{"nameUa", "Name(Ukrainian)"},
					{"nameEn", "Name"},
					{"price", "Price"},
					{"amount", "Amount"},
					{"date", "Date"},
					{"amountUnO", "Amount of unpayed orders"},
					{"sumUnO", "Sum of unpayed orders"},
					{"inBlacklist", "In black list"},
					{"addBlacklist", "Add to black list"},
					{"removeBlacklist", "Remove from black list"},
					{"alreadyThere", "Already there"},
					{"changeAmount", "Change Amount(if 0 - remove)"},
					{"order", "Order"},
					{"sum", "Sum"},
					{"setPayed", "Set payed"},
					{"message", "Message"},
					{"blackListMessage", "You're in black list. Pay your previous orders before doing new."},
					{"change", "Change"},
					{"existedUser", "User with enetered phone is already registered."}
			};
	
	@Override
	protected Object[][] getContents() {
		return content;
	}

}
