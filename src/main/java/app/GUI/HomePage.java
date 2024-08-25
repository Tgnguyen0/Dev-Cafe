package app.GUI;

import java.awt.*;

import app.InitFont.CustomFont;
import javax.swing.*;

public class HomePage extends JPanel {
    private CustomFont customFont = new CustomFont();

    public HomePage() {
        setPreferredSize(new Dimension(1100, 200)); //800, 500
        setLayout(new BorderLayout());
        setVisible(true);
        setFont(this.customFont.getFernandoFont(9));
        
        String imagePath = "dev_cafe/asset/coffee.gif";
        //File imageFile = new File(imagePath);

        // Tạo icon cho ảnh
        ImageIcon imageIcon = new ImageIcon(imagePath); // image_width: 2560, 1440

        // Tạo imageLabel cho ảnh
        JLabel imageLabel = new JLabel(imageIcon);
        // Thêm imageLabel vào optionMenu
        add(imageLabel, BorderLayout.WEST);

        // Thêm thanh chào mừng
        /*JLabel homeBanner = new JLabel("Chào mừng đến Dev_Cafe");
        homeBanner.setPreferredSize(new Dimension(350, 50));
        homeBanner.setFont(customFont.getFernandoFont().deriveFont(Font.PLAIN, 20));
        homePageIntro.add(homeBanner, BorderLayout.SOUTH);
    
        homePageContent.add(homePageIntro, BorderLayout.NORTH);
    
        JPanel homePageCenter = new JPanel();
        homePageCenter.setPreferredSize(new Dimension(200, 400));
        homePageCenter.setBackground(new Color(225, 203, 177));
    
        JLabel introductionParagrah1 = new JLabel("<html>Chào mừng bạn đến với Dev_Cafe, nơi hương vị của cà phê tươi mới hòa quyện hoàn hảo với sự náo nhiệt của sự đổi mới công nghệ.Nằm ở ngã tư của công nghệ tiên tiến và bầu không khí ấm áp của một quán cà phê, Dev_Cafe mang đến trải nghiệm độc đáo cho các đối tượng yêu công nghệ, doanh nhân và những người yêu thích cà phê.</html>");
        introductionParagrah1.setPreferredSize(new Dimension(700, 75));
        introductionParagrah1.setFont(customFont.getFernandoFont());
        homePageCenter.add(introductionParagrah1, BorderLayout.NORTH);

        JLabel introductionParagrah2 = new JLabel("<html>Tại Dev_Cafe, chúng tôi hiểu rằng công nghệ đóng vai trò quan trọng trong việc hình thành thế giới của chúng ta. Dù bạn là một nhà phát triển có kinh nghiệm, một nhà sáng lập startup, hay chỉ đơn giản là tò mò về những xu hướng công nghệ mới nhất, quán cà phê của chúng tôi cung cấp môi trường hoàn hảo để kích thích sự sáng tạo của bạn và kết nối với những người có cùng sở thích.</html>");
        introductionParagrah2.setPreferredSize(new Dimension(700, 75));
        introductionParagrah2.setFont(customFont.getFernandoFont());
        homePageCenter.add(introductionParagrah2, BorderLayout.NORTH);

        JLabel introductionParagrah3 = new JLabel("<html>Hãy tưởng tượng bạn đang thưởng thức ly espresso yêu thích của mình trong khi thảo luận về những tiến bộ mới nhất trong trí tuệ nhân tạo, ý tưởng cho dự án lập trình tiếp theo của bạn, hoặc tham gia một buổi hội thảo về công nghệ blockchain—tất cả đều diễn ra trong không gian chào đón của quán cà phê với trang trí hiện đại và sang trọng.</html>");
        introductionParagrah3.setPreferredSize(new Dimension(700, 75));
        introductionParagrah3.setFont(customFont.getFernandoFont());
        homePageCenter.add(introductionParagrah3, BorderLayout.NORTH);

        JLabel introductionParagrah4 = new JLabel("<html>Nhưng Dev_Cafe không chỉ là nơi để thưởng thức cà phê tuyệt vời và trò chuyện về công nghệ. Đó là một trung tâm cộng đồng nơi ý tưởng ra đời, hợp tác phát triển, và tạo ra những mối quan hệ. Nhân viên có kiến thức của chúng tôi luôn sẵn lòng pha một ly cà phê hoàn hảo và tạo điều kiện cho các cuộc thảo luận sôi nổi về mọi thứ từ phát triển phần mềm đến tiếp thị số.</html>");
        introductionParagrah4.setPreferredSize(new Dimension(700, 75));
        introductionParagrah4.setFont(customFont.getFernandoFont());
        homePageCenter.add(introductionParagrah4, BorderLayout.NORTH);

        JLabel introductionParagrah5 = new JLabel("<html>Dù bạn đang tìm kiếm nguồn cảm hứng cho dự án lớn tiếp theo của mình, khao khát sự trò chuyện thú vị, hay chỉ đơn giản là tìm kiếm một nơi thoải mái để làm việc từ xa, Dev_Cafe luôn chào đón bạn với tay mở rộng. Hãy đến với chúng tôi và khám phá một thế giới nơi công nghệ và cà phê gặp nhau để tạo ra một trải nghiệm khó quên. Chào mừng bạn đến với Dev_Cafe—nơi sự đổi mới chưa bao giờ ngon như vậy.</html>");
        introductionParagrah5.setPreferredSize(new Dimension(700, 75));
        introductionParagrah5.setFont(customFont.getFernandoFont());
        homePageCenter.add(introductionParagrah5, BorderLayout.NORTH);
        homePageContent.add(homePageCenter, BorderLayout.CENTER);

        JScrollPane homePageScrollPane = new JScrollPane(homePageContent);
        homePageScrollPane.setPreferredSize(new Dimension(900, 900));
        homePageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(homePageScrollPane, BorderLayout.CENTER);*/
    }

    /*@Override
    protected void paintComponent(Graphics g) {
        String imagePath = "dev_cafe/asset/coffee.gif"; // Path to your GIF image file
        File imageFile = new File(imagePath);

        //Chèn ảnh vào Option menu
        try {
            // Đọc ảnh từ file
            Image image = ImageIO.read(imageFile);

            // Tạo icon cho ảnh
            int newWidth = getWidth(); // Get the width of the panel
            int newHeight = getHeight(); // Get the height of the panel
            Image scaledImage = image; //getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            g.drawImage(scaledImage, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
