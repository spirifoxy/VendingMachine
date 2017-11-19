$(function() {
	
	$(".wallet-user tr td:last-child").click(function(){
	    var $this = $(this);
		var denom = $this.parent().find('td:first-child').html();
	    
	    $.post(window.location.pathname + "spendCoin",
	    {
	    	denom: denom
	    },
	    function(data, status){
	    	
	        data = $.parseJSON(data);
	        
	        if (data.error) {
	        	
	        	alert("Error: " + data.error);
	        } else {
	        	
		        var rowIndex = $this.parent().index() + 1; // +1 because of the header column
		        
		        $this.parent().find('td:nth-child(2)').html(data.userCoins);
		        $(".result-panel .total").html(data.currentPaid);
	        }
	    });
	});
	
	$(".result-panel button").click(function(){
	    var $this = $(this);
	    var $total = $(".result-panel .total");
		var amount = $total.html();
	    
	    $.post(window.location.pathname + "getChange",
	    {
	    	amount: amount
	    },
	    function(data, status){
	    	
	        if (data === "OK") {
	        	
	        	location.reload();
	        	
	        } else {
	        	
	        	alert("Error: unknown");
	        	
	        }
	    });
	});
	
});