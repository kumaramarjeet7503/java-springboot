function manageNav(){
    let sidebar = document.getElementById('sidebar') ;
    sidebar.style.display = sidebar.style.display == 'none' ? 'block' : 'none' ;
    let reopen =  document.getElementById('reopen') ;
    let content =  document.getElementById('content') ;
    if( sidebar.style.display == 'none' )
    {
        content.style.marginLeft = '2%' ;
       
    }else{
        content.style.marginLeft = '17%' ;
    }
}

function getSearch(e)
{
    $('#search-result').hide() ;
    if(e.value != '')
    {
        let url = `http://localhost:8080/search/${e.value}` ;
        fetch(url).then((response)=> response.json()).then((data)=> {
        
        if(data.length == 0) return ($('#search-result').hide() )  ;
            let text = `<div class="list-group">` ;
            data.forEach(element => {
                text += `<a href='/user/brief-contact/${element.cId}' class="list-group-item list-group-item-action" >${element.name} </a>` ; 
            });
            text += `</div>` ;
        $('#search-result').html(text) ;
        $('#search-result').show() ;
        }) ;
    }
    
}

const paymentStart = ()=>{
    let paymentField = $("#payment_field").val().trim() ;
    if(paymentField === undefined || paymentField === null || paymentField === '' ){
        console.log("Please fill the paymount amount")  ;
    }
    
    //  Ajax call
    $.ajax(
        {
            url:'/order/create',
            data:JSON.stringify({"paymentField":paymentField}),
            contentType : "application/json",
            type:'POST',
            dataType : "json",
            success:function(response){
                console.log(response) ;
                if(response.status === 'created')
                {
                    var options = {
                        "key": "rzp_test_35qE5WllMM5aQu", // Enter the Key ID generated from the Dashboard
                        "amount": response.amount, // Amount is in currency subunits. Default currency is
                        "currency": "INR",
                        "name": "Acme Corp",
                        "description": "Test Transaction",
                        "image": "https://example.com/your_logo",
                        "order_id":response.id, //This is a sample Order ID. Pass the
//                          Payment handler
                        "handler": function (response){
                            console.log(response) ;
                            console.log(response.razorpay_payment_id);
                            console.log(response.razorpay_order_id);
                            console.log(response.razorpay_signature);

                            updateOrder(response) ;

                            alert("Payment succesful") ;
                        },
                        "prefill": {
                            "name": "",
                            "email": "",
                            "contact": ""
                        },
                        "notes": {
                            "address": "Razorpay Corporate Office"                        
                        },
                        "theme": {
                            "color": "#3399cc"
                        }
                    };

                        //  Payment initiator
                        var rzp1 = new Razorpay(options);
                        rzp1.on('payment.failed', function (response){
                            console.log(response.error.code);
                            console.log(response.error.description);
                            console.log(response.error.source);
                            console.log(response.error.step);
                            console.log(response.error.reason);
                            console.log(response.error.metadata.order_id);
                            console.log(response.error.metadata.payment_id);
                        });
                        rzp1.open();
                        console.log("sucess");
                        
                }
            },
            error:function(error){
                console.log(error);
                alert("Something went wrong")
            }
        }
    )
}


function updateOrder(response)
{
    $.ajax(
        {    
            url:'/order/update',
            data:JSON.stringify({
                razorpay_payment_id: response.razorpay_payment_id,
                razorpay_order_id: response.razorpay_order_id,
                razorpay_status : "paid"
            }),
            contentType : "application/json",
            type:'POST',
            dataType : "json",
            success:function(response){
                alert("Your payment has been succesfully done") ; 
            },
            error: function(error){
                alert("error while updating order. We will reach you soon") ;
            }
        })
}