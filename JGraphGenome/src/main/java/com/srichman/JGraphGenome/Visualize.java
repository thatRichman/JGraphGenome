/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2021, Spencer Richman, <richmanspencer@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS  OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.srichman.JGraphGenome;

import com.mxgraph.layout.*;
import com.mxgraph.util.mxCellRenderer;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Visualize {

    public static String makeGraphPNG(Graph<String, DefaultWeightedEdge> graph) throws IOException {
        File imgFile = new File("C:\\users\\richm\\pictures\\test.png");
        imgFile.createNewFile();
        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter =
                new JGraphXAdapter<String, DefaultWeightedEdge>(graph);
        layoutGraph(graphAdapter);
        BufferedImage image =
                mxCellRenderer.createBufferedImage(
                        graphAdapter,
                        null,
                        2,
                        Color.WHITE,
                        true,
                        null);
        ImageIO.write(image, "PNG", imgFile);
        return imgFile.getAbsolutePath();
    }

    private static void layoutGraph(JGraphXAdapter graphAdapter){
        mxIGraphLayout layout = new mxCompactTreeLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());
    }

    private static void displayImage(String fn) throws IOException
    {
        BufferedImage img= ImageIO.read(new File(fn));
        ImageIcon icon=new ImageIcon(img);
        JFrame frame=new JFrame();
        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setBounds(50, 375, 250, 50);
        closeBtn.addActionListener(e -> frame.dispose());
        frame.setLayout(new FlowLayout());
        frame.setSize(800,500);
        JLabel lbl=new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.add(closeBtn);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void visualize(Graph<String, DefaultWeightedEdge> graph) throws IOException {
        String graphFn = makeGraphPNG(graph);
        displayImage(graphFn);
    }
}
