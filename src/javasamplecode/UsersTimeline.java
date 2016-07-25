package javasamplecode;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import com.mysql.jdbc.Blob;

import javafx.scene.control.ToggleButton;
import javafx.scene.effect.ImageInput;

@SuppressWarnings("unused")
public class UsersTimeline{
	JFrame frame = new JFrame();
	
	static User loggedinUser = new User();

	public void makeFrame(User u,int j,int k) throws IOException, SQLException, Exception {

		
		if(k==0){ frame.setVisible(false);new UsersTimeline().makeFrame(u,0,1);}    // conditon for loggedinuser
		if(j==1)loggedinUser=new CurrentUser(u).getAllData(u);                     // loggedinuser
		else
			u = new CurrentUser(u).getAllData(u);                             //
		frame.setSize(1400,900);
		//frame.setState(Frame.NORMAL);
		  JButton SearchButton;
		  JButton RefreshButton;
		  JButton HomeButton;
		  JButton UploadandeditButton;
		  JButton FollowersButton;
		  JLabel usersName;
		  JPanel leftPanel;
		  JPanel jPanel2;
		  JPanel ImagesPanel;
		  JScrollPane scrollPane;
		
        jPanel2 = new JPanel();
        usersName = new JLabel();
        SearchButton = new JButton();
        RefreshButton = new JButton();
        HomeButton = new JButton();
        leftPanel = new JPanel();
        UploadandeditButton = new JButton();
        FollowersButton = new JButton();
        scrollPane = new JScrollPane();
        ImagesPanel = new JPanel();

        jPanel2.setLayout(new java.awt.GridLayout(20, 20, 5, 5));

        frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        usersName.setFont(new java.awt.Font("Roboto", 2, 14));
        
        
        usersName.setText("  Hi   "+u.getFNAME()+"  "+u.getLNAME());

        SearchButton.setFont(new java.awt.Font("Roboto", 1, 12));
        SearchButton.setText("Search");
        
       // System.out.println("OOOOOOOOOOOOo");
        
        SearchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				 frame.setVisible(false);
				new search(frame);
			}
		});
        RefreshButton.setFont(new java.awt.Font("Roboto", 1, 12));
        RefreshButton.setText("Refresh");
       
       
        /*JButton DeleteAccountButton = new JButton();
        DeleteAccountButton
        .addActionListener(new Database());*/
        JButton LogoutButton  =new JButton("Log Out");LogoutButton.setFont(new java.awt.Font("Roboto", 1, 12));
        
        
        HomeButton.setFont(new java.awt.Font("Roboto", 1, 14));
        HomeButton.setText("Home");
        User CurrentUser = u;
        RefreshButton.addActionListener(new ActionListener() {///////////REFRESH
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					new UsersTimeline().makeFrame(CurrentUser,0,1);
					 frame.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
        
HomeButton.addActionListener(new ActionListener() {                         ///HOME
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					
					new UsersTimeline().makeFrame(loggedinUser,0,1);
					 frame.setVisible(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
//JPanel followPanel = new JPanel(new GroupLayout(host));
JPanel sidePanel = new JPanel(new GridLayout(20, 1));ImagesPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
sidePanel.add(UploadandeditButton);
Follow fa = new Follow();
System.out.println(u.getUID());
List<User> followingsList = fa.ListOfFollowers(u.getUID());

JButton FollowersListButton = new JButton("Followers List");
sidePanel.add(FollowersListButton);
sidePanel.setFont(new java.awt.Font("Roboto", 0, 12));
//sidePanel.setVisible(true);
FollowersListButton.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
//		sidePanel.setVisible(false);
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	//	sidePanel.setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
});
//JPanel hover = new JPanel(new GridLayout(3, 1));


if(followingsList.isEmpty())sidePanel.add(new JLabel("you are not following anyone"));
for (User temp:followingsList){
	JLabel tempLabel = new JLabel(temp.getUSERNAME());
	tempLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	
     
	sidePanel.add(tempLabel);
	tempLabel.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				if(temp.getUSERNAME().equals(loggedinUser.getUSERNAME()))new UsersTimeline().makeFrame(loggedinUser, 0,1);
				else
				new UsersTimeline().makeFrame(temp, 0,1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
}



sidePanel.addMouseListener(new MouseListener() {
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//hover.setVisible(false);
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//hover.setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
});
List<User> fllist = fa.ListOfFollowings(u.getUID());

sidePanel.add(new JButton("Following List"));sidePanel.setFont(new java.awt.Font("Roboto", 0, 12));
if(fllist.isEmpty())sidePanel.add(new JLabel("you are not being followed "));
for (User temp:fllist){
	JLabel tempLabel = new JLabel(temp.getUSERNAME());
	tempLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	//tempLabel.setBorderPainted( false );//JLabel
	//tempLabel.setPreferredSize(new Dimension(10, 10));
	//tempLabel.setSize(new Dimension(10, 10));
	sidePanel.add(tempLabel);
	tempLabel.addMouseListener(new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			try {
				if(temp.getUSERNAME().equals(loggedinUser.getUSERNAME()))new UsersTimeline().makeFrame(loggedinUser, 0,1);
				else
				new UsersTimeline().makeFrame(temp, 0,1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	});
	//stem.out.println(temp);
}

        
        if(u.equals(loggedinUser))
        	{
        	UploadandeditButton.setFont(new java.awt.Font("Roboto", 0, 12));
        	UploadandeditButton.setText("Upload New Images");
        	
        	UploadandeditButton.setVisible(true);
        	ActionListener uploadandeditListener = new Listeners.UploadAndEditListener(u);
            UploadandeditButton.addActionListener(uploadandeditListener);

        	}
        else
        {
        	//UploadandeditButton.setText("Follow");
        	if(new Follow().IsFollowing(loggedinUser.getUID(), u.getUID()))
			{
				//System.out.println("ghussa");
        		UploadandeditButton.setFont(new java.awt.Font("Roboto", 0, 12));
				UploadandeditButton.setText("Unfollow");
			}
			else
			{
				UploadandeditButton.setFont(new java.awt.Font("Roboto", 0, 12));
				UploadandeditButton.setText("Follow");
			}
        	User user = u;
			     UploadandeditButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						try {
							if(new Follow().IsFollowing(loggedinUser.getUID(), user.getUID()))
							
							{
								try
							{
								new Follow().DeleteFollower(loggedinUser.getUID(), user.getUID());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int tempCount=0;
							try {
								//new FollowActions().DeleteFollower(loggedinUser.getUID(), u.getUID());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							UploadandeditButton.setFont(new java.awt.Font("Roboto", 0, 12));
							UploadandeditButton.setText("follow");
							//UploadandeditButton.setText(Integer.toString(tempCount));
							}
							else
							{try {
								new Follow().AddFollower(loggedinUser.getUID(),user.getUID());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int tempCount=0;
							try {
								//tempCount = new ImageFunctions().LikeImage(temp);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							UploadandeditButton.setFont(new java.awt.Font("Roboto", 0, 12));
							UploadandeditButton.setText("Unfolllow");
							
							
							//likeCountLabel.setText(Integer.toString(tempCount));
							
								
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});

        }
        /*ImageIcon ic = new ImageIcon("C:\\Users\\Goyal Banna\\Desktop\\300.gif");
        JLabel jLabel1 = new JLabel();
        jLabel1 .setIcon(ic);
        sidePanel.add(jLabel1);*/
        ImagesPanel.setLayout(new java.awt.GridLayout(2,20, 5,5));
        
        FollowersButton.setFont(new java.awt.Font("Roboto", 0, 12));
        FollowersButton.setText("List Of Followers");
        
        LogoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					 frame.setVisible(false);
					 frame.dispose();
					 Login ISA = new Login();
					ISA.createGUI(ISA);
					//MySqlConnector.conn.close();
				} catch (ClassNotFoundException | SQLException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
       
        
		CurrentUser currentuser = null;
		try {
			currentuser = new CurrentUser(u);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
		Blob aBlob = currentuser.getProfilepicture();

		InputStream is = null;
		try {
			is = aBlob.getBinaryStream(1, aBlob.length());
		} catch (SQLException e1) {
		
			e1.printStackTrace();
		}
		BufferedImage imag = null;
		
		try {
			imag = ImageIO.read(is);
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
		Image image = imag;
		ImageIcon scaledImageIcon =new ImageIcon(image.getScaledInstance(180,150, Image.SCALE_DEFAULT));
		
		JLabel imageLabel = new JLabel();
        imageLabel.setIcon(scaledImageIcon);
        
        leftPanel.add(imageLabel);
      leftPanel.add(sidePanel);
     //   leftPanel.setLayout();
        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        
        //leftPanel.setLayout(leftPanelLayout);
        leftPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 109, Short.MAX_VALUE)
        );
        
        List<images> list = new ArrayList<images>();
        ImageFunctions imf = new ImageFunctions();
       // System.out.println(u.getFNAME());
        list = imf.DisplayImageData(u,loggedinUser);
        
        for(images temp : list){


    		JPanel ImagePanelhd = new javax.swing.JPanel();
    		ImagePanelhd.setSize(new Dimension(200,200));
            JLabel UsersImageLabel = new javax.swing.JLabel();
            JLabel CaptionLabel = new javax.swing.JLabel();
            CaptionLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            //setBorder();
            JButton likeUnlikeButton = new javax.swing.JButton();
            JButton viewButton = new javax.swing.JButton();
            JButton downloadButton = new javax.swing.JButton();
            JLabel likeCountLabel = new javax.swing.JLabel();
            String likersToolTip=new Like().DisplayLikers(temp.getIMAGE_ID());
    		//System.out.println(temp.getIMAGE_ID());
    		likeCountLabel.setToolTipText(likersToolTip);
            JButton PinButton = new javax.swing.JButton();
            JButton DeletePhotoButton = new javax.swing.JButton();

          //  frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
/*
            JPanel jPanel1hd = new JPanel();
			jPanel1hd.setLayout(new java.awt.GridLayout(20, 4, 3, 3));
*/
		//	users temp=temp;
			//System.out.println(temp.getUID());
        	Blob aBlob1 = (Blob) temp.getaBlob();

    		InputStream is1 = null;
    		try {
    			is1 = aBlob1.getBinaryStream(1, aBlob1.length());
    		} catch (SQLException e1) {
    		
    			e1.printStackTrace();
    		}
    		BufferedImage imag1 = null;
    		
    		try {
    			imag1 = ImageIO.read(is1);
    		} catch (IOException e1) {
    		
    			e1.printStackTrace();
    		}
    		Image image1 = imag1;
    		ImageIcon UsersImageIcon =new ImageIcon(image1.getScaledInstance(380,260,Image.SCALE_DEFAULT));
    		UsersImageLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    		ImagePanelhd.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
    		UsersImageLabel.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					UsersImageLabel.setToolTipText("Click to see Full String");
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					UsersImageLabel.setToolTipText("Click to see Full String");
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					UsersImageLabel.setToolTipText("Click to see Full Image");
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					UsersImageLabel.setToolTipText("Click to see Full Image");
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					JOptionPane.showMessageDialog(null, "", temp.getCAPTION(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image1.getScaledInstance(1000,600, Image.SCALE_DEFAULT)));
				    
					
				}
			});
    		UsersImageLabel.setIcon(UsersImageIcon);
           // UsersImageLabel.setText("Image");
            //CaptionLabel.setSize(200, 200);
    		
            CaptionLabel.setText("    "+temp.getCAPTION());CaptionLabel.setFont(new java.awt.Font("Roboto", 0, 12));


			if(new Like().HasLiked(u.getUID(),temp.getIMAGE_ID()))
			{
				//if(temp.getIMAGE_ID()==81)System.out.println("ghussa");
				likeUnlikeButton.setFont(new java.awt.Font("Roboto", 0, 12));
				likeUnlikeButton.setText("Unlike");
			}
			else
			{
				likeUnlikeButton.setFont(new java.awt.Font("Roboto", 0, 12));
				likeUnlikeButton.setText("Like");
			}
           
            ImageFunctions imff = new ImageFunctions();
            int Count = new ImageFunctions().UnlikeImage(temp);
            likeCountLabel.setText(Integer.toString(Count+1)+" likes");
            new ImageFunctions().LikeImage(temp);
            
			     
			     likeUnlikeButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							if(likeUnlikeButton.getText().equals("Unlike"))
							
							{
								try
							{
								new Like().DeleteLiker(loggedinUser.getUID(), temp.getIMAGE_ID());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int tempCount=0;
							try {
								tempCount = new ImageFunctions().UnlikeImage(temp);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							likeUnlikeButton.setFont(new java.awt.Font("Roboto", 0, 12));
							likeUnlikeButton.setText("Like");
							
							likeCountLabel.setFont(new java.awt.Font("Roboto", 0, 12));
							
							likeCountLabel.setText(Integer.toString(tempCount)+" likes");
							
							}
							
							else
							{try {
							new Like().AddLiker(loggedinUser.getUID(), temp.getIMAGE_ID());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							int tempCount=0;
							try {
								tempCount = new ImageFunctions().LikeImage(temp);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							likeUnlikeButton.setFont(new java.awt.Font("Roboto", 0, 12));
							likeUnlikeButton.setText("Unlike");
							
							likeCountLabel.setFont(new java.awt.Font("Roboto", 0, 12));
							//String text="lol";
							
							//likeCountLabel.setToolTipText(text);
							likeCountLabel.setText(Integer.toString(tempCount)+" likes");
							
								
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
				});
				
		
			     
			
				// likeUnlikeButton.setText("Like");
			
       
			viewButton.setFont(new java.awt.Font("Roboto", 0, 12));
            viewButton.setText("View");
            viewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "", temp.getCAPTION(), JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image1.getScaledInstance(1000,600, Image.SCALE_DEFAULT)));
			    }
					
				
			});

            downloadButton.setFont(new java.awt.Font("Roboto", 0, 12));
            downloadButton.setText("Download");
            downloadButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						ImageFunctions imF = new ImageFunctions();
						//System.out.println(temp.getIMAGE_ID());
						imF.DownloadImage(temp);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});

            //AbstractButton jLabel3;
			
            PinButton.setFont(new java.awt.Font("Roboto", 0, 12));
            
            PinButton.setText("Pin Photo");
            User tu = u;
            PinButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						if(tu.getUSERNAME().equals(loggedinUser.getUSERNAME())){
							new ImageFunctions().UploadImage(loggedinUser, temp);
							new ImageFunctions().DeleteImage(temp.getIMAGE_ID());
							
						}
						else
						{
							new ImageFunctions().UploadImage(loggedinUser, temp);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(
				            null, "Pinned To Your Timeline. Press Refresh");
				}
			});
          
            DeletePhotoButton.setFont(new java.awt.Font("Roboto", 0, 12));
            if(u.getUSERNAME().equals(loggedinUser.getUSERNAME())){
            DeletePhotoButton.setText("Delete");
            DeletePhotoButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						new ImageFunctions().DeleteImage(temp.getIMAGE_ID());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(
				            null, "Deleted From Timeline. Press Refresh!!");
				}
			});}


            javax.swing.GroupLayout ImagePanelLayout = new javax.swing.GroupLayout(ImagePanelhd);
            ImagePanelhd.setLayout(ImagePanelLayout);
            ImagePanelLayout.setHorizontalGroup(
                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ImagePanelLayout.createSequentialGroup()
                    .addGap(10,10,10)
                    .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(CaptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(UsersImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ImagePanelLayout.createSequentialGroup()
                            .addComponent(viewButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(PinButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ImagePanelLayout.createSequentialGroup()
                        		.addComponent(likeUnlikeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(likeCountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                             .addComponent(DeletePhotoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                   // .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    //.addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            ImagePanelLayout.setVerticalGroup(
                ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ImagePanelLayout.createSequentialGroup()
                    .addGap(10,10,10)
                    .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(UsersImageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ImagePanelLayout.createSequentialGroup()
                            .addComponent(downloadButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(viewButton)
                                .addComponent(PinButton))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(likeUnlikeButton)
                                .addComponent(likeCountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    			
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(ImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CaptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeletePhotoButton))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

    		
    		ImagesPanel.add(ImagePanelhd);
    		}
		ImagesPanel.setLayout(new java.awt.GridLayout(5,20,10,10));
        
        scrollPane.setViewportView(ImagesPanel);
        
        frame.setIconImage(image);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( frame.getContentPane());
        frame.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usersName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    //.addComponent(UploadandeditButton, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        
                    //.addComponent(FollowersButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    )
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(HomeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addComponent(LogoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPane)
                       // .addComponent(component, min, pref, max)
                        //.addComponent(scrollPane,  javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()
                        
                        )))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usersName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchButton)
                    .addComponent(RefreshButton)
                    .addComponent(HomeButton).addComponent(LogoutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       // .addComponent(UploadandeditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        //.addComponent(FollowersButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        )
                    .addGroup(layout.createSequentialGroup()
                        //.addComponent(scrollPane)
                        //.addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        
                        .addContainerGap())))
        );
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
      
    }
}
