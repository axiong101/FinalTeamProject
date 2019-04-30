package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class QuizGraph {
  private TreeMap<String, ArrayList<QuestionNode>> graph; // Arraylist that contains an arraylist of
                                                    // strings
  // index 0 of the second arraylist holds the vertexes in
  // the graph.
  // the index after 0 hold the other vertexes that it points
  // to
  private int questionNum;
  private int topicNum;
  private Set<String> topicSet;
  private List<String> questionList;
  

  /*
   * Default no-argument constructor
   */
  public QuizGraph() {
    graph = new TreeMap<String, ArrayList<QuestionNode>>();
    questionNum = 0;
    topicNum = 0;
    topicSet = new TreeSet<String>();
    questionList = null;
  }

  /**
   * Add new vertex to the graph.
   *
   * If vertex is null or already exists, method ends without adding a vertex or throwing an
   * exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void addTopic(String vertex) {
    if (vertex != null) {
      graph.put(vertex, new ArrayList<QuestionNode>());
      topicNum++;
    }
  }

  /**
   * Remove a vertex and all associated edges from the graph.
   * 
   * If vertex is null or does not exist, method ends without removing a vertex, edges, or throwing
   * an exception.
   * 
   * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in the graph
   */
  public void removeTopic(String vertex) {
    if (vertex != null) {
      if (graph.containsKey(vertex)) {
        graph.remove(vertex);
        topicNum--;
      }
    }
  }

  /**
   * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and unweighted) If either
   * vertex does not exist, no edge is added and no exception is thrown. If the edge exists in the
   * graph, no edge is added and no exception is thrown.
   * 
   * Valid argument conditions: 1. neither vertex is null 2. both vertices are in the graph 3. the
   * edge is not in the graph
   */
  public void addQuestion(String topic, QuestionNode question) {
    if (topic != null && question != null) {
      if (graph.containsKey(topic) && graph.containsKey(question)) {
        if (!graph.get(topic).contains(question) || !graph.get(question).contains(topic)) {
          graph.get(topic).add(question);
          questionNum++;
        }
      } else if (!graph.containsKey(topic)) {
        addTopic(topic);
        addQuestion(topic, question);
      }
    }
  }



  /**
   * Returns a Set that contains all the vertices
   * 
   */
  public Set<String> getAllTopics() {
    return topicSet = new TreeSet<String>(graph.keySet());
  }

  /**
   * Get all the neighbor (adjacent) vertices of a vertex
   *
   */
  public List<QuestionNode> getTopicQuestions(String topic) {

    return graph.get(topic);
  }


  /**
   * Returns the number of edges in this graph.
   */
  public int questionNum() {
    return questionNum;
  }

  /**
   * Returns the number of vertices in this graph.
   */
  public int topicNum() {
    return topicNum;
  }
}

