
const POLL_INTERVAL_MS = 1000;

const processResult = function(result) {
	if (result === 'WAIT') {
		$(".rps-result").text("Waiting for result");
		setTimeout(rpsPoll, POLL_INTERVAL_MS);
	} else {
		$(".rps-result").text(result);
		$(".rps-choice")
			.removeClass("disabled")
			.prop("disabled", false);
	}
}

const rpsPoll = function() {
	$.ajax({
		url: "PollResult",
		success: processResult
	});
};

const rpsSubmit = function(choice) {
	$(".rps-result").text("");
	$(".rps-choice")
		.addClass("disabled")
		.prop("disabled", true);
	$.ajax({
		url: "Submit",
		data: {
			"choice": choice
		},
		success: processResult
	});
};

$(document).ready(function() {
	$(".rps-choice").off("click.rps").on("click.rps", function() {
		const choice = $(this).val();
		rpsSubmit(choice);
	});
});

