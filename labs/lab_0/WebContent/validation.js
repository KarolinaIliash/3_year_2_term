
function validatePhone() {
      	var x, text;
      	// Get the value of the input field with id="phone"
      	x = document.getElementById("phone").value;
      	if (!x.match(/^\d{10}$/)) {
          	lang = document.getElementById("lang").value;
      		if(lang == "en")
        		text = "Phone should consists only from digits and have length 10";
      		else
      			text = "Номер телефону має містити тільки цифри та мати довжину в 10 символів";
          	document.getElementById("notValid").innerHTML = text;
          	return false;
      	}
      	return true;
}

function validatePrice() {
  	var x, text;
  	// Get the value of the input field with id="phone"
  	x = document.getElementById("price").value;
  	if (!x.match(/^\d+\.\d{0,2}$/)) {
      	lang = document.getElementById("lang").value;
  		if(lang == "en")
    		text = "Price should be a real number, have dot and maximum two numbers after dot";
  		else
  			text = "Ціна має бути дійсним числом, мати крапку і максимум два символи після неї";
      	document.getElementById("notValid").innerHTML = text;
      	return false;
  	}
  	return true;
}