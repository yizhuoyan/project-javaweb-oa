;
+function(window, document) {
	window.addEventListener("load", function() {
		document.querySelectorAll("input[autosize]").forEach(function(inputEL) {
			inputEL.addEventListener("keydown", function(evt) {

				if (calculateStringPx(this) + 15 >= inputEL.offsetWidth) {
					// resize
					inputEL.style.width = (inputEL.offsetWidth * 2) + "px";
				}

			}, false);

		});
	});
	var calculateStringPx = function(input) {
		var style = window.getComputedStyle(input);
		var fontSize = style.fontSize;
		fontSize=parseInt(fontSize,10);
		var inputValue = input.value;
		return fontSize * inputValue.length;
	}

}(window, document);