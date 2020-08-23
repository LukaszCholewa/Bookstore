function validateForm() {
  var password = document.getElementById("passwordField").value;
  var regExp1 = /.*[0-9]+.*/;
  var regExp2 = /.{8,}/;
  return regExp1.test(password) && regExp2.test(password);
}