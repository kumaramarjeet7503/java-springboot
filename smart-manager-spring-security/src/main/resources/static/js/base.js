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