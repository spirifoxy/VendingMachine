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
	    
	    $.post(window.location.pathname + "getChange",
	    {
	    },
	    function(data, status){
	    	
	    	data = $.parseJSON(data);
	    	
	        if (data.message === "OK") {
	        	
	        	location.reload();
	        	
	        } else {
	        	
	        	alert("Error: " + data.error );s
	        	
	        }
	    });
	});
	
	$(".product-list .product").click(function(){
	    var $this = $(this);
	    var name = $this.find('.name').html();
		
	    $.post(window.location.pathname + "buyProduct",
	    {
	    	productName: name
	    },
	    function(data, status){
	    	
	    	data = $.parseJSON(data);
	    	
	        if (data.message) {
	        	alert(data.message);
	        	location.reload();
	        	
	        } else {
	        	
	        	alert("Error: " + data.error );
	        	
	        }
	    });
	});
	
});