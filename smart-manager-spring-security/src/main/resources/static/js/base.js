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
        $('#search-result').show() ;
    }
    
}