package governance;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import edu.uci.ics.jung.algorithms.layout.Layout;

import edu.uci.ics.jung.algorithms.layout.TreeLayout;

import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.RenderContext;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import org.apache.commons.collections15.functors.ConstantTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

import java.util.Collection;
import java.util.HashSet;

import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.subLayout.TreeCollapser;

import tipiDati.ID_GestoreDiAssemblea;

public class Configurazione {
	public int timeBetweenReport;
	/* Nome del file di configurazione */
	private String pathFile;
	/*---- Struttura ad albero dei gestori che compongono il sistema */
	private Albero_Gestori alberoGestori;

	/*---- Informazioni personali */
	private ID_GestoreDiAssemblea iam;
	private String folderOfHandlerMessage=null;
	
	public Configurazione(String string) {
		// TODO Auto-generated constructor stub
		alberoGestori=new Albero_Gestori();
		
		pathFile=string;
		
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder;
		
			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse (new File(pathFile));
			
			// normalize text representation
			doc.getDocumentElement ().normalize ();
			
			//--- Prima legge la struttura delle assemble			
			readStrutturaGestoreDiAssemblea(doc);
			
			//--- Identifica se stesso
			this.iam=readPersonalInformation(doc);
			
			
		} catch (SAXParseException err) {
			System.out.println ("** Parsing error" + ", line " 
					+ err.getLineNumber () + ", uri " + err.getSystemId ());
			System.out.println(" " + err.getMessage ());

		} catch (SAXException e) {
			Exception x = e.getException ();
			((x == null) ? e : x).printStackTrace ();

		}catch (Throwable t) {
			t.printStackTrace ();
		}	
	}


	private void readStrutturaGestoreDiAssemblea(Document doc) {
		// TODO Auto-generated method stub
		NodeList listOf_GestoreDiAssemblea = doc.getElementsByTagName(XML_CONFIGURATION_TAG.GESTORE_DI_ASSEMBLEA);
		
		for (int k=0;k<listOf_GestoreDiAssemblea.getLength();k++){
			   Node item = listOf_GestoreDiAssemblea.item(k);
			  	     
			   if(item.getNodeType() == Node.ELEMENT_NODE){
				   
				   Element elem=(Element)listOf_GestoreDiAssemblea.item(k);

				   /**/				   
				   NodeList nodelist_Name= elem.getElementsByTagName(XML_CONFIGURATION_TAG.NOME_GESTORE);		   
				   NodeList textNode = nodelist_Name.item(0).getChildNodes();					
				   String name=((Node)textNode.item(0)).getNodeValue().trim();
				  
				   /**/
				   NodeList nodelist_Ip= elem.getElementsByTagName(XML_CONFIGURATION_TAG.ALPHA_ADDRESS);		   
				   NodeList textNode_Ip= nodelist_Ip.item(0).getChildNodes();					
				   String alpha_address=((Node)textNode_Ip.item(0)).getNodeValue().trim();

				   String beta_address=null;
				   String gamma_address=null; 
				   /* TODO Prevedere la gestione degli alpha/beta address */				   
				   if (false) {
				    nodelist_Ip= elem.getElementsByTagName(XML_CONFIGURATION_TAG.BETA_ADDRESS);
				    if (nodelist_Ip!=null) {
				    	System.out.println(nodelist_Ip);
				    	textNode_Ip= nodelist_Ip.item(0).getChildNodes();
				    	beta_address=((Node)textNode_Ip.item(0)).getNodeValue().trim();				    	
				    }

				    /**/
				    nodelist_Ip= elem.getElementsByTagName(XML_CONFIGURATION_TAG.GAMMA_ADDRESS);		   
				    if (nodelist_Ip!=null) {
				        textNode_Ip= nodelist_Ip.item(0).getChildNodes();
					   gamma_address=((Node)textNode_Ip.item(0)).getNodeValue().trim();
				    }
				   }
				   GestoreDiAssemblea gest=new GestoreDiAssemblea(name,alpha_address,beta_address,gamma_address);
				  
				   /*--- Nell'albero bisogna aggiungere il GestoreDiAssemblea letto */
				   alberoGestori.addGestoreDiAssemblea(gest);
			}
		}
		
		/*--- Apre un form per visualizzare la struttura dell'albero */
		PrintStrutturaGestoreDiAssemblea();
	}

	
	
	private void PrintStrutturaGestoreDiAssemblea() {
		// TODO Auto-generated method stub
	   	
		//---
		 final Layout<GestoreDiAssemblea,String> layout = new TreeLayout<>(alberoGestori.getTree());

	    final TreeCollapser collapser;

	 
        collapser = new TreeCollapser();

	  final VisualizationViewer<GestoreDiAssemblea, String> vv=new VisualizationViewer<>(layout);
	  

	  vv.setPreferredSize(new Dimension(350,350)); //Sets the viewing area size
	  vv.setBackground(Color.white);
	  

	  RenderContext<GestoreDiAssemblea, String> rc = vv.getRenderContext();
	
	  rc.setEdgeShapeTransformer(new EdgeShape.Line());
	  rc.setVertexLabelTransformer(new ToStringLabeller());
	  // add a listener for ToolTips
	  vv.setVertexToolTipTransformer(new ToStringLabeller());
       vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));


 
      
      JFrame frame = new JFrame("Struttura dei \"Gestore di assemblea\"");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(vv);
    
      //--- Aggiungiamo il GestoreDiAssemblea dello zoom 
      final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
      frame.getContentPane().add(panel);
      
      final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();
      vv.setGraphMouse(graphMouse);

   
      JComboBox modeBox = graphMouse.getModeComboBox();
      modeBox.addItemListener(graphMouse.getModeListener());
      graphMouse.setMode(DefaultModalGraphMouse.Mode.TRANSFORMING);

      final ScalingControl scaler = new CrossoverScalingControl();

      JButton plus = new JButton("+");
      plus.addActionListener(new ActionListener() {
        
    	  @Override
    	  public void actionPerformed(ActionEvent e) {
              scaler.scale(vv, 1.1f, vv.getCenter());
          }

		
      });
      JButton minus = new JButton("-");
      minus.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              scaler.scale(vv, 1/1.1f, vv.getCenter());
          }
      });
      
      JButton collapse = new JButton("Collapse");
      collapse.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              Collection<GestoreDiAssemblea> picked =new HashSet(vv.getPickedVertexState().getPicked());
              if(picked.size() == 1) {
              	Object root = picked.iterator().next();
                  Forest<GestoreDiAssemblea,String> inGraph = (Forest<GestoreDiAssemblea, String>) layout.getGraph();

                  try {
						collapser.collapse(vv.getGraphLayout(), inGraph, root);
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

                  vv.getPickedVertexState().clear();
                  vv.repaint();
              }
          }});


      JButton expand = new JButton("Expand");
      expand.addActionListener(new ActionListener() {

          public void actionPerformed(ActionEvent e) {
              Collection picked = vv.getPickedVertexState().getPicked();
              for(Object v : picked) {
                  if(v instanceof Forest) {
                      Forest inGraph = (Forest)layout.getGraph();
          			collapser.expand(inGraph, (Forest)v);
                  }
                  vv.getPickedVertexState().clear();
                 vv.repaint();
              }
          }});

      JPanel scaleGrid = new JPanel(new GridLayout(1,0));
      scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

      //---------
      JPanel contentPane=(JPanel) frame.getContentPane();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
     
      JPanel controls = new JPanel();
      scaleGrid.add(plus);
      scaleGrid.add(minus);
      controls.add(scaleGrid);
      controls.add(modeBox);
      controls.add(collapse);
      controls.add(expand);
      contentPane.add(controls, BorderLayout.SOUTH);

      frame.pack();
      frame.setVisible(true);		
	}

	public ID_GestoreDiAssemblea readPersonalInformation(Document doc) {
		// TODO Auto-generated method stub
		NodeList elem_list = doc.getElementsByTagName(XML_CONFIGURATION_TAG.WHO_AM_I);
				
		Element elem=(Element) elem_list.item(0);
		if(elem.getNodeType() == Node.ELEMENT_NODE){
			NodeList nodelist_Name= elem.getElementsByTagName(XML_CONFIGURATION_TAG.NOME_GESTORE);
			NodeList textNode = nodelist_Name.item(0).getChildNodes();
			String name=((Node)textNode.item(0)).getNodeValue().trim();

			iam=new ID_GestoreDiAssemblea(name);
			
			nodelist_Name= elem.getElementsByTagName(XML_CONFIGURATION_TAG.TIME_BEETWEEN_REPORT);
			textNode = nodelist_Name.item(0).getChildNodes();
			String time=((Node)textNode.item(0)).getNodeValue().trim();
			this.timeBetweenReport=	5;
			System.out.println("timeBetweenReport:["+timeBetweenReport+"]");
 
			
		} else {
			System.out.println("!!! ERRORE nella lettura del file di configurazione ");
			System.exit(-1);
			iam=null;
		}		
		/**/
		NodeList nodelist_folder= elem.getElementsByTagName(XML_CONFIGURATION_TAG.FOLDER_HANDLER);
		NodeList textNode = nodelist_folder.item(0).getChildNodes();
				
		folderOfHandlerMessage=((Node)textNode.item(0)).getNodeValue().trim();
	
		return iam;
	}

	public static void main(String[]args) {
		Configurazione c=new Configurazione("input/test_base.xml");
		System.out.println(" Testing classe Configurazione.java");
		System.out.println(" Hello world by  ["+c.whoAmI()+"]");
		System.out.println(" that's all folks");
		
		
	}

	public String whoAmI() {
		// TODO Auto-generated method stub
		return iam.getId();		
	}

	public String getFolderOfHandlerMessage() {
		// TODO Auto-generated method stub
		return folderOfHandlerMessage;
	}

}
