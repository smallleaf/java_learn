import gui.ava.html.parser.HtmlParser;
import gui.ava.html.parser.HtmlParserImpl;
import gui.ava.html.renderer.ImageRenderer;
import gui.ava.html.renderer.ImageRendererImpl;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @description:
 * @author: 叶胜
 * @createTime: 2025-02-13 18:17
 **/
public class PicTest {

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        HtmlParser htmlParser = new HtmlParserImpl();
        String content = FileUtils.readFileToString(new File("/Users/yesheng/Documents/workspace/java_learn/sse/src/test/resources/test.html"));
        htmlParser.loadHtml(content);
        ImageRenderer imageRenderer = new ImageRendererImpl(htmlParser);
        imageRenderer.setWidth(955);
        imageRenderer.saveImage("/Users/yesheng/Documents/workspace/java_learn/sse/src/test/resources/test.png");
        System.out.println("===" + (System.currentTimeMillis() - start));
    }


}


