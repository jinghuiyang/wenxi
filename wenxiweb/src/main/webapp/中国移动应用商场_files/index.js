function initLize(){
      var browser=navigator.appName ;
      if(browser=="Microsoft Internet Explorer"){
         var b_version=navigator.appVersion 
         var version=b_version.split(";"); 
         var trim_Version=version[1].replace(/[ ]/g,""); 
         if(trim_Version=="MSIE8.0"){
          if($(window).width()<1400){
              $(".innerPic").css('left','1%');
              $("#scancode").css({'marginLeft':'200px','top':'16%'});
          }else if($(window).width()>1400 && $(window).width()<1750){
              $(".innerPic").css('left','-3%');
            }
        
         }
      }
}
initLize();
$(function(){
     var getH=$(window).height();
     var getW=$(window).width();
    $(".transhow,.mainer .list,.mainer").height(getH);
    if(getW<1000){
			$(".mainer .list").width(1000);
		}else{
			$(".mainer .list").width(getW);
		}

    $(".the_route_way").find(".son_itemize").bind("click",function () {
     	if($(this).hasClass("on")){
     		return ;
     	}else{
     		$(this).addClass("on").siblings().removeClass("on");
     	}
     });

//   首页轮播图配置
  
		 $(".temper").height($(window).height());
		 $(".innerPic").height($(".temper").height()*.7);
		 $(".innerPic").width('auto');
		 $(".innerPic").css({"top":$(".temper").height()/2-$(".innerPic").height()/2});
    var shader=(function(){

      function fadeClick(obj){
        var capper_pa = obj.outBox;

        var listO = obj.outBox.find(".list");

        var sideDotted = obj.iClod;
        var list_L= listO.length;
        var sideDottedI='';
        var index=0,state=0;

        listO.hide().eq(0).show();
        $(".mainer").show();
        if(list_L>1){
          for(var i=0; i<list_L; i++){
              sideDottedI += "<i></i>";
          }
         sideDotted.html(sideDottedI);
        }else{
          return false;
        }
        sideDotted.find("i").eq(0).addClass("on");
        sideDotted.find("i").click(function(){
           clearInterval(sy_timer);
           state = $(this).index();
          if(state == index)
            return false;
          else{
            runTime();
          }
       
        });

        function runTimeDotted(){
          sideDotted.find("i").removeClass('on').eq(state).addClass("on");
        };

        function runTime(){
          listO.find(".innerPic").hide();
          listO.eq(index).fadeOut(1500);
          listO.eq(state).fadeIn(1500);
          listO.eq(state).find(".innerPic").fadeIn(2000);
        //  sy_timer = setInterval(autoTab,6000);
          runTimeDotted();
          index = state;
        };
        var sy_timer = setInterval(autoTab,6000);
        sideDotted.find("i").hover(function(){
          clearInterval(sy_timer);

        },function(){
          sy_timer = setInterval(autoTab,6000);
        });
        function autoTab(){
          state++;
             
          if(state>list_L-1){state=0;}
          runTime();
        }
      }
      return {
        fadeClick : fadeClick
      }



     })()


shader.fadeClick({
  outBox: $(".mainer"),
  iClod: $(".transhow").find(".slider")
})






//   手机版 鼠标移动	
		$(".nav_more_entry").find(".mpver").hover(function(){
			$(this).find(".qr_code_pop").show();
		},function(){
			$(this).find(".qr_code_pop").hide();
		});





//   点击二级导航栏效果
$(".sortize_area_fix a").bind("click",function(){
  $(".sortize_area_fix a").removeClass("on");
  $(this).addClass("on");
});

    
//   应用商城首页轮播
      var bannerTran = (function(){
        function mainLog(objIn){
          var tranMain = objIn.mainBlock,
            tranWidth = tranMain.width(),
            transiBox = objIn.inSertI,
            outLine = objIn.outLine,
            strI = '',iNow=0,iAhead=0;
            tranMain.css({"left":tranWidth}).eq(0).css({"left":0});
            if(tranMain.length>1){
              for(var i=0; i<tranMain.length; i++){
                  strI += "<i></i>"
              }
              transiBox.html(strI);
              transiBox.find("i").eq(0).addClass("on");
              transiBox.find("i").bind("click",function(){
                  iAhead = $(".banner_animate_btn i.on").index();
                  iNow=$(this).index();
                  $(this).addClass("on").siblings().removeClass("on");        
                  if(iNow==iAhead){
                    return
                  }else if(iNow>iAhead){
                    tranMain.css({"left":tranWidth}).eq(iAhead).css({"left":0})
                    tranMain.eq(iNow).stop().animate({'left':0},1000);
                    tranMain.eq(iAhead).stop().animate({'left':-tranWidth},1000);
                  }else{
                    tranMain.css({"left":-tranWidth}).eq(iAhead).css({"left":0})
                    tranMain.eq(iNow).stop().animate({'left':0},1000);
                    tranMain.eq(iAhead).stop().animate({'left':tranWidth},1000);
                  }


              });
              function autoTrans(){
                aAhead = $(".banner_animate_btn i.on").index();
                aAhead == $(".banner_animate_btn i").length-1 ? aNow = 0 : aNow = aAhead+1;
                transiBox.find("i").eq(aNow).click();
              };  
              var Ttime = setInterval(autoTrans,6000);
              outLine.hover(function(){
                  clearInterval(Ttime);
              },function(){
                  Ttime = setInterval(autoTrans,6000);
              })

              }else{
                return false;
              }
              }
  
          return {
            mainLog : mainLog
          }
      })();

      bannerTran.mainLog({
        mainBlock: $('.banner_list_box .each_banlist_pic'),    
        inSertI: $(".banner_animate_btn"),
        outLine :$(".banner_list_box")
      })





//移动专区  鼠标移入效果
$(".zq_common_btn").find(".zq_app_display").hover(function(){
  $(this).find(".app_classify").hide().end().find(".download_icon").show();
  $(this).find(".qr_code_pop").show();
},function(){
   $(this).find(".download_icon").hide().end().find(".app_classify").show();
  $(this).find(".qr_code_pop").hide();
});

//装机必备区  鼠标移入效果
$(".rank_charts_area").find(".rank_chart_list").hover(function(){
  $(this).find(".dolo").hide().end().find(".doloic").show().end().find(".flow_asspect").addClass('on');
},function(){
   $(this).find(".doloic").hide().end().find(".dolo").show().end().find(".flow_asspect").removeClass('on');;

});

//专题区  鼠标移入效果
$(".com_area_recommend").find(".re_software_push").hover(function(){
  $(this).find(".sw_push_hot").hide().end().find(".sw_push_theload").show();
  $(this).find(".qr_code_pop").show();
},function(){
  $(this).find(".sw_push_theload").hide().end().find(".sw_push_hot").show();
  $(this).find(".qr_code_pop").hide();
});







// 回到顶部
$(window).bind("scroll", function(){ 
  var scl_top = $(this).scrollTop();
  if($("#goAhead").length>0){
    scl_top > 350 ? $("#goAhead").show() : $("#goAhead").hide()
  }

  //滚动后游戏分类栏目固定 
  if($(".tabulate_assort_whole").length>0){
  var obj_lan = $(".tabulate_assort_whole");
  var def_height = $(".tabulate_main_cont").offset().top - $("#the_ahead_wrap").outerHeight(true);
  if(scl_top > def_height){
      obj_lan.addClass("again_fix");
  }else{
      obj_lan.removeClass("again_fix");

  }
  }


});

$("#goAhead").bind("click",function(){
    $('body,html').animate({ scrollTop: 0 }, 600);
    return false;
})   







//ie下bannerlsit 模糊图片隐藏  -->应用商城首页

function isIE()  
{  
    if(!!window.ActiveXObject || "ActiveXObject" in window)  
        return true;  
    else  
        return false;  
}  
if(isIE()){$(".each_banlist_pic").find(".banner_btn_part .paper").hide();}




/************************游戏应用列表页*****************************/
$(".tabul_mx_include .each_mx_noun").bind("click",function(){
  if($(this).hasClass("on")){
    return false;
  }else{
    $(".tabul_mx_include .each_mx_noun").removeClass("on");
    $(this).addClass("on");
  }
})


$(".addit_sift_slap .slap_tag_tier").bind("click",function(){
  if($(this).hasClass("on")){
    return false;
  }else{
    $(this).addClass("on").siblings('.slap_tag_tier').removeClass('on');
  }
});

$(".tabulate_pack .tabulate_sub_column").hover(function(){
  $(this).find(".sub_column_load").addClass('on');
},function(){
  $(this).find(".sub_column_load").removeClass('on');
})





/************************分页*****************************/

$("#gen_page_refresh .dir_each_detail").bind("click",function(){
  $(this).addClass('on').siblings(".dir_each_detail").removeClass("on");
});




/************************应用详情页--轮播*****************************/
$(function(){
  var mainobj = $(".mainbl_reveal_area .reveal_train"),
    secobj = mainobj.find(".true_pie"),
    thiobj = mainobj.find(".trans_innertu"),
    basic_list = mainobj.find(".true_pie_pic"),
    arrObj = mainobj.find(".the_arrow"),
    preOne = mainobj.find(".left_ons"),
    nextOne = mainobj.find(".right_ons"),
    direction = 1;
    if(basic_list.length < 4){
      arrObj.hide();
      return false;
    }
    
    secobj.width(basic_list.outerWidth(true)*basic_list.length);
    preOne.css({'top':basic_list.height()/2-preOne.height()/2,'left':-preOne.width()});
    preOne.hide();
    nextOne.css({'top':basic_list.height()/2-nextOne.height()/2,'right':-nextOne.width()});


    arrObj.bind("click",function(){
      if($(this).hasClass("right_ons")){
        direction = 1;
        if(secobj.is(":animated")){
          return false;
        }else{
           direPlay();
        }
        
      }else{
        direction = 0;
          if(secobj.is(":animated")){
          return;
        }else{
           direPlay();
        }
      }

    })


    function direPlay(){
      var left_vals = secobj.position().left;
      if(direction){
        secobj.animate({'left':left_vals-basic_list.outerWidth(true)},500,arrShow);
      }else{
        secobj.animate({'left':left_vals+basic_list.outerWidth(true)},500,arrShow);
      };

    };

    function arrShow(){
      var l_val = secobj.position().left;
      l_val == 0 ?  preOne.hide() : preOne.show();
      -l_val + thiobj.outerWidth(true) >= secobj.outerWidth(true) ? nextOne.hide() : nextOne.show();
    }
});


/************************应用详情页--展开应用介绍******************************/

$(".cont_hold .control_cnt").bind("click",function(){
  if($(this).hasClass("on")){
    $(this).removeClass("on").find(".cnt_word_txt").text('展开');
    $(".rounded_mainbl_left .the_flesh_out").removeClass("on");
  }else{
    $(this).addClass("on").find(".cnt_word_txt").text('收起');
       $(".rounded_mainbl_left .the_flesh_out").addClass("on");

  
  }
});

// 20171123
$(".mf_info_follow .get_ori_comment").bind("click",function(){
      var topH = $("#the_ahead_wrap").outerHeight(true);
  $('body,html').animate({ scrollTop: $(".mainbl_reveal_area .cmt_clot_title").offset().top - topH}, 600);
})







})

$(window).resize(function(){
    var getH=$(window).height();
    var getW=$(window).width();
    $(".temper").height(getH);
    $(".innerPic").height($(".temper").height()*.7);
    $(".innerPic").width('auto');
    $(".innerPic").css({"top":$(".temper").height()/2-$(".innerPic").height()/2});
    $(".transhow,.mainer .list,.mainer").height(getH);
    if(getW<1000){
      $(".mainer .list").width(1000);
    }else{
      $(".mainer .list").width(getW);
    }

    initLize();

})




