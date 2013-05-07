<%@ page import="org.springframework.security.core.userdetails.User"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="java.util.Collection"%>
<%@ page import="javax.swing.text.AbstractDocument"%>
<%@ page import="org.springframework.security.core.GrantedAuthority"%>
<!DOCTYPE html>
<html lang="cz">
<head>
<meta charset="utf-8" />
<title>Home Page</title>
  <link href="resources/css/bootstrap2.2.css" rel="stylesheet">
  
  
</head>
<body>
	<div class="navbar-wrapper" id="home">
		<div class="container">

			<div class="navbar navbar-inverse">
				<div class="navbar-inner">
					<a class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse"> <span class="icon-bar"></span> <span
						class="icon-bar"></span> <span class="icon-bar"></span>
					</a> <a class="brand" href="#">GENEPI - PACIENT</a>
				</div>
			</div>
		</div>
	</div>

	<%
		User user = (User) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		String username = user.getUsername();
		Collection<GrantedAuthority> authorities = user.getAuthorities();
	%>
	
	<div class="container-fluid">
      
        <div class="span3">
			<div class="well sidebar-nav">
				</br></br></br></br></br></br></br></br></br></br></br></br></br>
          		<h1><a href="/home/" style="text-decoration: none">FN MOTOL</a></h1>
        	</div>
		
			<div class="well sidebar-nav">
				<ul class="nav nav-list">
					<li class="nav-header">Cislo pacienta:</li>
             	 	<li><a href="#">Prehled</a></li>
              		<li><a href="#">Anamneza</a></li>
              		<li><a href="#">Farmakoterapie</a></li>
              		<li><a href="#">Neurologicke nalezy</a></li>
              		<li><a href="#">Neuropsychologie</a></li>
              		<li><a href="#">(Neuropsychologie - old)</a></li>
              		<li><a href="#">Diagnosticke testy</a></li>
              		<li><a href="#">Neuropsychologie</a></li>
              			<ul>
              				<li><a href="#">Skalpove EEG</a></li>
              				<li><a href="#">Neurozobraz. testy</a></li>
              			</ul>
              		<li><a href="#">Invazivni testy</a></li>
              			<ul>
              				<li><a href="#">ECoG</a></li>
              				<li><a href="#">iEEG</a></li>
              				<li><a href="#">Kortikalni mapovani</a></li>
              			</ul>
             		 <li><a href="#">Operace</a></li>
             		 <li><a href="#">Komplikace</a></li>
              		<li><a href="#">Outcome</a></li>
            	</ul>
			</div>
          
			<div class="well sidebar-nav">
            	<ul class="nav nav-list">
            	  <li class="nav-header">Pacienti</li>
             	 <li><a href="patientsList">Kartoteka pacientu</a></li>
             	 <li><a href="#">Pokrocile vyhledavani</a></li>
             	 <li class="nav-header">Uzivatel: <%=username%></li>
             	 <li><a href="myProfile">Profil</a></li>
             	 <li><a href="j_spring_security_logout">Odhlasit</a></li>
             	 <li class="nav-header">Jazyk</li>
            	</ul>
          	</div>
			
		</div>
      	
		<div class="span9">
			<div class="hero-unit">
           		<div>
            		<h2 class="pull-left">Prehled pacienta</h2>
            		<h3 class="pull-right"><a href="#">export pacienta</a></h3>
					<table style="border: 1px solid black">
						<tbody>
							<tr>
								<th>Cislo pacienta: </th>
									<td>XXXX</td>

								<th>Rodne cislo: </th>
									<td>XXXX</td>

								<th>Adresa: </th>
									<td>XXXX</td>

							</tr>
							<tr>
								<th>Telefon: </th>
									<td>XXXX</td>
									
								<th>Vek:</th>
									<td>XX let</td>

								<th>Pohlavi:</th>
									<td>XXX</td>
							</tr>
							
			                 <tr>
			                 	<th>Email:</th>
									<td>XXXX</td>
									
								<th>Vek pri zacatku epilepsie:</th>
									<td>23 let (trvani: 0 let)</td>

								<th>Osetrujici lekar:</th>
									<td>Nevyplneno</td>

							</tr>
						</tbody>
					</table>
				</div>
                                            
				<div>
					<h3>Anamneza</h3>
					<table style="border: 1px solid black" class="span7">
        				<thead style="border: 1px solid black">
        					<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
        				</thead>
        				<tbody>	
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Epilepsie v rodine</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Prenat�ln� rizika</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Febriln� krece</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Z�net CNS</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>�raz CNS</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Operace CNS</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Casn� PMD retardace</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Zac�tek epilepsie</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Prvn� z�chvat s horeckou</td>						
                    							</tr>
                    					
                    							<tr>
                        							<td>Infantiln� spasmy</td>
                    							</tr>
                    					
                    							<tr>
                    							    <td>Epileptick� syndrom</td>
                     							    <td></td>
                    							</tr>
                   						
                   								<tr>
													<td>Non CNS komorbidita</td>
	                        				 		<td></td>
	                    						</tr>
	                    					
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    				</tbody>
    			</table>
			</div>
					
				<div>
					<h3>Zachvaty</h3>
					<table style="border: 1px solid black" class="span7">
        				<thead style="border: 1px solid black">
        					<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
        				</thead>
        				<tbody>	
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Cetnost z�chvatu</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Sekund�rne generalizovan�</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Status epilepticus</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Neepileptick� z�chvaty</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>V�skyt</td>
                    							</tr>
	                    					
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
	                    						
	                    						<tr>
	                       							<td colspan="2">1. typ z�chvatu:</td>
	                    						</tr>
	                    						
	                    						<tr>
                        							<td>Zad�no dne</td>
                    							</tr>
	                    						
	                    						<tr>
                        							<td>SSC klasifikace</td>
                    							</tr>
	                    					
	                    						<tr>
                        							<td>ILAE klasifikace</td>
                    							</tr>
	                    					
	                    						<tr>
                        							<td>Komentar</td>
                        							<td></td>
                    							</tr>
	                    					
	                    					
	                    						
	                    						<tr>
	                       							<td colspan="2">2. typ z�chvatu:</td>
	                    						</tr>
	                    						
	                    						<tr>
	                       							<td colspan="2">3. typ z�chvatu:</td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    				</tbody>
    			</table>
				</div>
					
				<div>
					<h3>Farmakoterapie</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Datum</td>
            					<td>Lecivo</td>
            					<td>Efektivita</td>
            					<td>Pri operaci</td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td></td>
            					<td></td>
            					<td></td>
            					<td></td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Neurologicky nalez</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Dominance hemisf�ry</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Abnorm�ln� neurologick� n�lez</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Hemipar�za</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Defekt zorn�ho pole</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Detaily neurologick�ho n�lezu</td>
                    							</tr>
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Neuropsychologie</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Neuropsychologick� vysetren�</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Vek pri neuropsychologick�m vysetren�</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Inteligencn� �roven</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Specifick� porucha ucen�</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>V�vojov� porucha reci</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>ADHD syndrom</td>
                    							</tr>
                    							
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Diagnosticke testy - Skalpove EEG</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Diagnostick� test - Skalpov� EEG</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Z�kladn� EEG aktivita</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>EEG zpomalen�</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Interikt�ln� EEG hroty</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Lokalizace interikt�ln�ch EEG hrotu</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>EEG status epilepticus</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Sekund�rn� bilater�ln� synchronie</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Ikt�ln� EEG vzorce</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace ikt�ln�ch EEG vzoru</td>
                    							</tr>
                    							
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Diagnosticke testy - Neurozobrazovaci testy</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Diagnostick� test - Neurozobrazovac� test</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>MRI protokol</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>MRI n�lez</td>
                    							</tr>
                    
                    							<tr>
                        							<td>MRI popis</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>FDG PET</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace PET hypometabolismu</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Interikt�ln� SPECT</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace SPECT hypoperfuse</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Ikt�ln� SPECT</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace SPECT hyperperfuse</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>SISCOM</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>MRS protokol</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>MRS n�lez</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace MRS abnormality</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>fMRI</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Detaily fMRI</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>DTI</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Detaily DTI studie</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>WADA</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Detaily WADA</td>
                    							</tr>
                    							
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Invazivni testy - ECoG</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Invazivn� test ECoG</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>ECoG pokryt�</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>ECoG vzorce</td>
                    							</tr>
                    
                    							<tr>
                        							<td>ECoG po resekci</td>
                    							</tr>
                    					
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Invazivni testy - iEEG</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Invazivn� test iEEG</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Intrakrani�ln� elektrody</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Lokalizace intrakrani�ln�ch elektrod</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Invazivn� EEG zpomalen�</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Invazivn� EEG interikt�ln� hroty</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace invazivn�ch EEG interikt�ln�ch hrotu</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Invazivn� EEG status epilepticus</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Invazivn� EEG ikt�ln� vzorce</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizce invazivn�ch EEG ikt�ln�ch vzorcu</td>
                    							</tr>
                    					
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Invazivni testy - Kortikalni mapovani</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Invazivn� test Kortik�ln� mapov�n�</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Kortik�ln� mapov�n�</td>
                    							</tr>
                    					
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Operace</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Zadano dne: XX.XX.XXXX</td>
            					<td class="pull-right"><a href="#">Zobrazit vsechny zaznamy</a></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td colspan="2">
                					<div>
                						<table>
                    						<tbody>
                    							<tr>
                        							<td>Datum operace</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Vek pri operaci</td>
                    							</tr>
                    					
                    							<tr>
                        							<td>Trv�n� epilepsie v dobe operace</td>
                    							</tr>
                    
                    							<tr>
                        							<td>Typ operace</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Rozsah operace</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Lokalizace operace</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>MST</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Kalostomie</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>VNS</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Datum imlantace VNS</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Detaily operace</td>
                    							</tr>
                    							
                    							<tr>
                        							<td>Resekce kompletn�</td>
                    							</tr>
                    					
	                    						<tr>
	                       							<td>Koment�r</td>
	                        						<td></td>
	                    						</tr>
                							</tbody>
                						</table>
                					</div>
           						 </td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Histologie</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Datum</td>
            					<td>Histopatologie</td>
            					<td>Klasifikace FCD</td>
            					<td></td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td></td>
            					<td></td>
            					<td></td>
            					<td></td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Komplikace</h3>
					<table style="border: 1px solid black" class="span7">
						<thead style="border: 1px solid black">
							<tr>
            					<td>Datum</td>
            					<td>Prubeh</td>
            					<td>Typ komplikace</td>
            					<td>Komplikace</td>
        					</tr>
						</thead>
        				<tbody>
        					<tr>
            					<td></td>
            					<td></td>
            					<td></td>
            					<td></td>
        					</tr>
    					</tbody>
    				</table>
				</div>
					
				<div>
					<h3>Outcome</h3>
				</div>
			</div>
		</div>
	</div>
	<div class="span3">
    	<div id="copyright">
        			<p>GENEPI, &copy 2013, FIT CVUT</p>
		</div>
	</div>
</body>
</html>

    
      