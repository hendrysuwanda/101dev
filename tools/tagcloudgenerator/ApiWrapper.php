<?php

require_once("../wikibotClasses/wikibot.classes.php");

function getWpapi(){
   $bot = 'TestBot';
   $wiki = '101companies';
   $wpapi = new wikipediaapi ('', '', '', $bot, $wiki, true);
   
   return $wpapi;
}

function getAllPages(){
 $pages = getWpapi()->listprefix("");
 var_dump($pages);
 return $pages;
}

function getRivison($title,$age){
  return getWpapi()->revisions($title, 1, $age, false);
}

function getAllLanguages(){
 $langs = getWpapi()->listprefix("Language:");
 return $langs;	
}


function getAllImplementations(){
   echo "FOO";
   $impl = getWpapi()->listprefix("101implementation:");
   var_dump($impl); 
   return $impl;
}

function getAllTechnologies(){
 $technologies = getWpapi()->listprefix("Technology:");
// var_dump($technologies);
 return $technologies;
}

function getPageContent($title){
 $page = getWpapi()->revisions($title, 1, 'older', true);
 $content = $page[0]['*'];
 //var_dump($content);
 return $content;
}

function extractTechnologies($text){
 $pattern = "/(\[{2}Technology:)([\w\W\s][^\]]+)(\]{2})/"; 
 preg_match_all($pattern, $text, $out, PREG_PATTERN_ORDER); 
 if(count($out) == 0) return array();
 $technologyNames = array();
 foreach($out[2] as $tn){   //make flat array
    array_push($technologyNames, $tn);
 }
  
 return $technologyNames;
}

function extractLanguages($text){
 $pattern = "/(\[{2}Language:)([\w\W\s][^\]]+)(\]{2})/"; 
 preg_match_all($pattern, $text, $out, PREG_PATTERN_ORDER); 
 if(count($out) == 0) return array();
 $languageNames = array();
 foreach($out[2] as $ln){   //make flat array
    array_push($languageNames, $ln);
 }
  
 return $languageNames;
}


function getSubCategories($categoryTitle){
     $categoryNames = array();
     $subCats = getWpapi()->categorymembers("category:" . $categoryTitle);
      foreach($subCats as $sc){
          if(substr_count($sc['title'],"Category") == 1){ //found a Category
            $t = str_replace("Category:", "", $sc['title']); // take Category:Test as input and produce only Test
            array_push($categoryNames, $t);
            $sc = getSubCategories($t);
            if(count($sc) > 0){
             foreach($sc as $s){   //make flat array, as soon we do not track dependencies
               array_push($categoryNames, $s);
             }
            }
          }           
      } 
      
      return $categoryNames;
}

function getCategories(){
   $categories = getWpapi()->listcategories();
   $categoryNames = array();

   foreach($categories as $c){
      array_push($categoryNames, $c['*']);
      $subCategories = getSubCategories($c['*']);
      foreach($subCategories as $sc){
       array_push($categoryNames, $sc);
      }
   }
   
   return $categoryNames;
}

function flattern($arr, &$all){
  if(count($arr) > 0){
   foreach($arr as $el){
    array_push($all, $el);
   }   
  }
}

