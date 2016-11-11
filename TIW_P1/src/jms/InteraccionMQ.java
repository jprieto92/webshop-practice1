package jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;

import jms.InformacionProperties;
import javax.annotation.Resource;

public class InteraccionMQ {

	private javax.jms.ConnectionFactory factory = null;
	private javax.naming.InitialContext contextoInicial = null;
	private javax.jms.Destination cola = null;
	private javax.jms.Connection Qcon = null;
	private javax.jms.Session QSes = null;
	private javax.jms.MessageProducer Mpro = null;
	private javax.jms.MessageConsumer Mcon = null;

//	@Resource(mappedName = "jms/cf1.1")
//	private static ConnectionFactory connectionFactory;
//	
//	@Resource(mappedName = "jms/queue1.1")
//	private static Queue queue;

	public void escrituraMQ(MessageChat mensaje, String selector) {

		try {

			contextoInicial = new javax.naming.InitialContext();
			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Queue) contextoInicial.lookup(InformacionProperties.getQueue());
			Qcon =factory.createConnection();
			QSes = Qcon.createSession(false,javax.jms.QueueSession.AUTO_ACKNOWLEDGE);

			Mpro = QSes.createProducer(cola);

			javax.jms.ObjectMessage men = QSes.createObjectMessage();
			//javax.jms.ObjectMessage men = QSes.createTextMessage();
			men.setObject(mensaje);
			//men.setText(mensaje);
			men.setJMSCorrelationID(selector);
			Qcon.start();
			Mpro.send(men);

			this.Mpro.close();
			this.QSes.close();
			this.Qcon.close();
			System.out.println("Mensaje escrito en la cola");

		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

	}

	public MessageChat lecturaMQ(String strSelectorPasado) {

		MessageChat listaMensajes = new MessageChat();
		try {
			contextoInicial = new javax.naming.InitialContext();

			factory = (javax.jms.ConnectionFactory) contextoInicial.lookup(InformacionProperties.getQCF());
			cola = (javax.jms.Queue) contextoInicial.lookup(InformacionProperties.getQueue());
			Qcon = factory.createConnection();			
			QSes = Qcon.createSession(false,javax.jms.QueueSession.AUTO_ACKNOWLEDGE);

			String sSelector = "JMSCorrelationID = '" + strSelectorPasado.trim() + "'";
			Mcon = QSes.createConsumer(cola, sSelector);
			Qcon.start();
			Message mensaje = null;
			System.out.println("VOY A LEER DE LA COLA");
			while (true) {
				mensaje = Mcon.receive(100);
				if (mensaje != null) {
					if (mensaje instanceof ObjectMessage) {
						ObjectMessage m = (ObjectMessage) mensaje;
						System.out.println(m.getObject());
						listaMensajes = (MessageChat) m.getObject();
						//System.out.println(listaMensajes.getText());
					} else {
						// JHC ************ No es del tipo correcto
						break;
					}
				} else // NO existe mensaje, mensaje es null
				{
					System.out.println("NO HAY MAS MENSAJES POR RECIBIR");
					break;
				}
			}
			this.Mcon.close();
			this.QSes.close();
			this.Qcon.close();
		} catch (javax.jms.JMSException e) {
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().getMessage());
			System.out
					.println(".....JHC *************************************** Error de JMS: "
							+ e.getLinkedException().toString());
		} catch (Exception e) {
			System.out
					.println("JHC *************************************** Error Exception: "
							+ e.getMessage());
		}

		return listaMensajes;

	}
}
